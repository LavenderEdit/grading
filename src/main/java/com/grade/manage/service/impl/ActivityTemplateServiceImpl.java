package com.grade.manage.service.impl;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.summary.TemplateSummary;
import com.grade.manage.dto.update.TempAssignUpdateDTO;
import com.grade.manage.exception.UserNotFoundException;
import com.grade.manage.mapper.TempAssignMapper;
import com.grade.manage.model.ActivitySet;
import com.grade.manage.model.ActivitySetItem;
import com.grade.manage.model.ActivitySetItemId;
import com.grade.manage.model.ActivityTemplate;
import com.grade.manage.model.Assignment;
import com.grade.manage.model.User;
import com.grade.manage.repository.ActivitySetItemRepository;
import com.grade.manage.repository.ActivitySetRepository;
import com.grade.manage.repository.ActivityTemplateRepository;
import com.grade.manage.repository.AssignmentRepository;
import com.grade.manage.repository.UserRepository;
import com.grade.manage.service.ActivityTemplateService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Studios TKOH!
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ActivityTemplateServiceImpl implements ActivityTemplateService {

    private final ActivityTemplateRepository activityTemplateRepository;
    private final AssignmentRepository assignmentRepository;
    private final ActivitySetRepository activitySetRepository;
    private final ActivitySetItemRepository activitySetItemRepository;
    private final UserRepository userRepository;
    private final TempAssignMapper tempAssignMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TemplateSummary> listAll() {
        return tempAssignMapper.toSummaryList(activityTemplateRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TemplateSummary> listActive() {
        return tempAssignMapper.toSummaryList(activityTemplateRepository.findByActiveTrue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TemplateSummary> listActiveByWeekday(Integer weekday) {
        return tempAssignMapper.toSummaryList(activityTemplateRepository.findByActiveTrueAndWeekday(weekday));
    }

    @Override
    @Transactional(readOnly = true)
    public TemplateSummary getSummary(Long id) {
        return tempAssignMapper.toSummary(getTemplateOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public TempAssignDetailDTO getDetail(Long id) {
        ActivityTemplate template = activityTemplateRepository.findWithRelationsById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity template with id %d was not found".formatted(id)));

        Optional<Assignment> latestAssignment = template.getAssignments().stream()
                .filter(assignment -> assignment.getDate() != null)
                .max(Comparator.comparing(Assignment::getDate));

        if (latestAssignment.isPresent()) {
            return tempAssignMapper.toDetail(latestAssignment.get());
        }

        return tempAssignMapper.toTemplateDetail(template);
    }

    @Override
    public TempAssignDetailDTO create(TempAssignCreateDTO create) {
        ActivityTemplate template = tempAssignMapper.toTemplate(create);
        ActivityTemplate savedTemplate = activityTemplateRepository.save(template);

        linkTemplateToSetIfPresent(savedTemplate, create.getSetId());

        Assignment assignment = tempAssignMapper.toAssignment(create);
        assignment.setTemplate(savedTemplate);
        assignment.setUser(resolveUser(create.getUser_id()));

        Assignment savedAssignment = assignmentRepository.save(assignment);
        savedTemplate.getAssignments().add(savedAssignment);

        return tempAssignMapper.toDetail(savedAssignment);
    }

    @Override
    public TempAssignDetailDTO update(TempAssignUpdateDTO update) {
        if (update.getTemplateId() == null) {
            throw new IllegalArgumentException("Template id is required for updating a template");
        }

        ActivityTemplate template = getTemplateOrThrow(update.getTemplateId());
        tempAssignMapper.updateTemplate(update, template);

        Assignment updatedAssignment = null;
        if (update.getAssignmentId() != null) {
            updatedAssignment = resolveAssignmentForTemplate(template, update.getAssignmentId());
            tempAssignMapper.updateAssignment(update, updatedAssignment);
            if (update.getUserId() != null) {
                updatedAssignment.setUser(resolveUser(update.getUserId()));
            }
            assignmentRepository.save(updatedAssignment);
        }

        activityTemplateRepository.save(template);

        if (updatedAssignment != null) {
            return tempAssignMapper.toDetail(updatedAssignment);
        }
        return tempAssignMapper.toTemplateDetail(template);
    }

    @Override
    public void delete(Long id) {
        ActivityTemplate template = getTemplateOrThrow(id);

        template.getRelations().forEach(relation -> relation.getSet().getRelations().remove(relation));
        activityTemplateRepository.delete(template);
    }

    private void linkTemplateToSetIfPresent(ActivityTemplate template, Long setId) {
        if (setId == null) {
            return;
        }
        ActivitySet set = activitySetRepository.findById(setId)
                .orElseThrow(() -> new EntityNotFoundException("Activity set with id %d was not found".formatted(setId)));

        int nextOrder = activitySetItemRepository.findBySet_IdOrderBySortOrderAsc(setId).stream()
                .map(ActivitySetItem::getSortOrder)
                .max(Integer::compareTo)
                .map(order -> order + 1)
                .orElse(1);

        ActivitySetItem relation = ActivitySetItem.builder()
                .id(new ActivitySetItemId(set.getId(), template.getId()))
                .set(set)
                .template(template)
                .sortOrder(nextOrder)
                .build();

        template.getRelations().add(relation);
        set.getRelations().add(relation);
        activitySetItemRepository.save(relation);
    }

    private ActivityTemplate getTemplateOrThrow(Long id) {
        return activityTemplateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity template with id %d was not found".formatted(id)));
    }

    private Assignment resolveAssignmentForTemplate(ActivityTemplate template, Long assignmentId) {
        return template.getAssignments().stream()
                .filter(assignment -> assignment.getId().equals(assignmentId))
                .findFirst()
                .orElseGet(() -> assignmentRepository.findWithDetailsById(assignmentId)
                .filter(assignment -> assignment.getTemplate().getId().equals(template.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Assignment with id %d was not found for template".formatted(assignmentId))));
    }

    private User resolveUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d was not found".formatted(userId)));
    }
}
