package com.grade.manage.service;

import com.grade.manage.dto.create.RoleCreateDTO;
import com.grade.manage.dto.detail.RoleDetailDTO;
import com.grade.manage.dto.summary.RoleSummary;
import com.grade.manage.dto.update.RoleUpdateDTO;
import com.grade.manage.service.generic.DtoCrudService;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface RoleService extends DtoCrudService<RoleDetailDTO, RoleCreateDTO, RoleUpdateDTO, Long> {

    RoleSummary getSummaryByName(String name);

    List<RoleSummary> listSummaries();
}
