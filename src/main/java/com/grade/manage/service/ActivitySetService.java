package com.grade.manage.service;

import com.grade.manage.dto.create.SetCreateDTO;
import com.grade.manage.dto.detail.SetDetailDTO;
import com.grade.manage.dto.simple.SetDTO;
import com.grade.manage.dto.summary.SetSummary;
import com.grade.manage.dto.update.SetUpdateDTO;
import com.grade.manage.service.generic.DtoCrudService;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface ActivitySetService extends DtoCrudService<SetDTO, SetCreateDTO, SetUpdateDTO, Long> {

    SetDetailDTO getDetail(Long id);

    List<SetSummary> listSummaries();
}
