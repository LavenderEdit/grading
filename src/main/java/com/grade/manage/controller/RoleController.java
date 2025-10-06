package com.grade.manage.controller;

import com.grade.manage.controller.generic.DtoCrudController;
import com.grade.manage.dto.create.RoleCreateDTO;
import com.grade.manage.dto.detail.RoleDetailDTO;
import com.grade.manage.dto.summary.RoleSummary;
import com.grade.manage.dto.update.RoleUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Studios TKOH!
 */
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController implements DtoCrudController<RoleDetailDTO, RoleCreateDTO, RoleUpdateDTO, Long> {

    private final RoleService roleService;

    @Override
    public ResponseEntity<ApiResponse<List<RoleDetailDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Roles fetched successfully", roleService.findAllDto()));
    }

    @Override
    public ResponseEntity<ApiResponse<RoleDetailDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Role fetched successfully", roleService.findDtoById(id)));
    }

    @Override
    public ResponseEntity<ApiResponse<RoleDetailDTO>> create(@Valid @RequestBody RoleCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("Role created successfully", roleService.create(create)));
    }

    @Override
    public ResponseEntity<ApiResponse<RoleDetailDTO>> update(@Valid @RequestBody RoleUpdateDTO updated) {
        return ResponseEntity.ok(ApiResponse.ok("Role updated successfully", roleService.update(updated)));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Role deleted successfully"));
    }

    @GetMapping("/summaries")
    public ResponseEntity<ApiResponse<List<RoleSummary>>> listSummaries() {
        return ResponseEntity.ok(ApiResponse.ok("Role summaries fetched successfully", roleService.listSummaries()));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse<RoleSummary>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(ApiResponse.ok("Role fetched successfully", roleService.getSummaryByName(name)));
    }
}
