package com.grade.manage.service.impl;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.simple.TempAssignDTO;
import com.grade.manage.dto.update.AssignmentUpdateDTO;
import com.grade.manage.dto.update.TempAssignUpdateDTO;
import com.grade.manage.exception.UserNotFoundException;
import com.grade.manage.mapper.TempAssignMapper;
import com.grade.manage.model.ActivityTemplate;
import com.grade.manage.model.Assignment;
import com.grade.manage.model.User;
import com.grade.manage.repository.ActivityTemplateRepository;
import com.grade.manage.repository.AssignmentRepository;
import com.grade.manage.repository.UserRepository;
import com.grade.manage.service.ActivityTemplateService;
import com.grade.manage.service.AssignmentService;
import com.grade.manage.service.util.ServiceUtils;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
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
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final ActivityTemplateRepository activityTemplateRepository;
    private final UserRepository userRepository;
    private final TempAssignMapper tempAssignMapper;
    private final ActivityTemplateService activityTemplateService;

    @Override
    @Transactional(readOnly = true)
    public List<TempAssignDTO> listAll() {
        return tempAssignMapper.toDtoList(assignmentRepository.findAllBy());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TempAssignDTO> listByUser(Long userId) {
        return tempAssignMapper.toDtoList(assignmentRepository.findByUser_IdOrderByDateDesc(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TempAssignDTO> listByDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates are required");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date must be greater than start date");
        }
        return tempAssignMapper.toDtoList(assignmentRepository.findByDateBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public TempAssignDetailDTO getDetail(Long id) {
        Assignment assignment = assignmentRepository.findWithDetailsById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment with id %d was not found".formatted(id)));
        return tempAssignMapper.toDetail(assignment);
    }

    @Override
    public TempAssignDetailDTO create(TempAssignCreateDTO create) {
        return activityTemplateService.create(create);
    }

    @Override
    public TempAssignDetailDTO update(AssignmentUpdateDTO update) {
        if (update.getId() == null) {
            throw new IllegalArgumentException("Assignment id is required for updates");
        }
        Assignment assignment = assignmentRepository.findWithDetailsById(update.getId())
                .orElseThrow(() -> new EntityNotFoundException("Assignment with id %d was not found".formatted(update.getId())));

        ServiceUtils.patchIfNotNull(update.getDate(), assignment::setDate);
        ServiceUtils.patchIfNotNull(update.getStatus(), assignment::setStatus);
        ServiceUtils.patchIfNotNull(update.getGrade_status(), assignment::setGradeStatus);
        ServiceUtils.patchIfNotNull(update.getEvidence_url(), assignment::setEvidenceUrl);

        if (update.getUser_id() != null) {
            assignment.setUser(resolveUser(update.getUser_id()));
        }

        TempAssignUpdateDTO templateUpdate = update.getTemplate();
        if (templateUpdate != null) {
            ActivityTemplate template = assignment.getTemplate();
            if (templateUpdate.getTemplateId() != null && !templateUpdate.getTemplateId().equals(template.getId())) {
                throw new IllegalArgumentException("Template id does not match the assignment template");
            }
            templateUpdate.setTemplateId(template.getId());
            templateUpdate.setAssignmentId(assignment.getId());

            tempAssignMapper.updateTemplate(templateUpdate, template);
            tempAssignMapper.updateAssignment(templateUpdate, assignment);

            if (templateUpdate.getUserId() != null) {
                assignment.setUser(resolveUser(templateUpdate.getUserId()));
            }

            activityTemplateRepository.save(template);
        }

        Assignment saved = assignmentRepository.save(assignment);
        return tempAssignMapper.toDetail(saved);
    }

    @Override
    public void delete(Long id) {
        Assignment assignment = assignmentRepository.findWithDetailsById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment with id %d was not found".formatted(id)));

        ActivityTemplate template = assignment.getTemplate();
        template.getAssignments().removeIf(existing -> existing.getId().equals(assignment.getId()));
        assignmentRepository.delete(assignment);
    }

    private User resolveUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d was not found".formatted(userId)));
    }
}
