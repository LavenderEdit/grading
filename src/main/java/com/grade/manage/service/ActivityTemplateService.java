package com.grade.manage.service;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.summary.TemplateSummary;
import com.grade.manage.dto.update.TempAssignUpdateDTO;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface ActivityTemplateService {

    List<TemplateSummary> listAll();

    List<TemplateSummary> listActive();

    List<TemplateSummary> listActiveByWeekday(Integer weekday);

    TemplateSummary getSummary(Long id);

    TempAssignDetailDTO getDetail(Long id);

    TempAssignDetailDTO create(TempAssignCreateDTO create);

    TempAssignDetailDTO update(TempAssignUpdateDTO update);

    void delete(Long id);
}
