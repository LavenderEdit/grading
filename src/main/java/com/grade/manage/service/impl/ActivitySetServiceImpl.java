package com.grade.manage.service.impl;

import com.grade.manage.dto.create.SetCreateDTO;
import com.grade.manage.dto.detail.SetDetailDTO;
import com.grade.manage.dto.simple.SetDTO;
import com.grade.manage.dto.summary.SetSummary;
import com.grade.manage.dto.update.SetUpdateDTO;
import com.grade.manage.mapper.SetMapper;
import com.grade.manage.model.ActivitySet;
import com.grade.manage.repository.ActivitySetRepository;
import com.grade.manage.service.ActivitySetService;
import jakarta.persistence.EntityNotFoundException;
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
public class ActivitySetServiceImpl implements ActivitySetService {

    private final ActivitySetRepository activitySetRepository;
    private final SetMapper setMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SetDTO> findAllDto() {
        return setMapper.toDtoList(activitySetRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public SetDTO findDtoById(Long id) {
        return setMapper.toDto(getSetOrThrow(id));
    }

    @Override
    public SetDTO create(SetCreateDTO create) {
        ActivitySet entity = setMapper.toEntity(create);
        ActivitySet saved = activitySetRepository.save(entity);
        return setMapper.toDto(saved);
    }

    @Override
    public SetDTO update(SetUpdateDTO update) {
        ActivitySet entity = getSetOrThrow(update.getId());
        setMapper.updateEntity(update, entity);
        ActivitySet saved = activitySetRepository.save(entity);
        return setMapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        ActivitySet entity = getSetOrThrow(id);
        activitySetRepository.delete(entity);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public SetDetailDTO getDetail(Long id) {
        ActivitySet set = activitySetRepository.findWithTemplatesById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity set with id %d was not found".formatted(id)));
        return setMapper.toDetail(set);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SetSummary> listSummaries() {
        return setMapper.toSummaryList(activitySetRepository.findAll());
    }

    private ActivitySet getSetOrThrow(Long id) {
        return activitySetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity set with id %d was not found".formatted(id)));
    }
}
