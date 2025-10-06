package com.grade.manage.service;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.simple.TempAssignDTO;
import com.grade.manage.dto.update.AssignmentUpdateDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface AssignmentService {

    List<TempAssignDTO> listAll();

    List<TempAssignDTO> listByUser(Long userId);

    List<TempAssignDTO> listByDateRange(LocalDateTime start, LocalDateTime end);

    TempAssignDetailDTO getDetail(Long id);

    TempAssignDetailDTO create(TempAssignCreateDTO create);

    TempAssignDetailDTO update(AssignmentUpdateDTO update);

    void delete(Long id);
}
