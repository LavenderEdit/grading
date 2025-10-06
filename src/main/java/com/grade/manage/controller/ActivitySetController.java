package com.grade.manage.controller;

import com.grade.manage.controller.generic.DtoCrudController;
import com.grade.manage.dto.create.SetCreateDTO;
import com.grade.manage.dto.detail.SetDetailDTO;
import com.grade.manage.dto.simple.SetDTO;
import com.grade.manage.dto.summary.SetSummary;
import com.grade.manage.dto.update.SetUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.ActivitySetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/activity-sets")
@RequiredArgsConstructor
@Validated
public class ActivitySetController implements DtoCrudController<SetDTO, SetCreateDTO, SetUpdateDTO, Long> {

    private final ActivitySetService activitySetService;

    @Override
    public ResponseEntity<ApiResponse<List<SetDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Activity sets fetched successfully", activitySetService.findAllDto()));
    }

    @Override
    public ResponseEntity<ApiResponse<SetDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Activity set fetched successfully", activitySetService.findDtoById(id)));
    }

    @Override
    public ResponseEntity<ApiResponse<SetDTO>> create(@Valid @RequestBody SetCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("Activity set created successfully", activitySetService.create(create)));
    }

    @Override
    public ResponseEntity<ApiResponse<SetDTO>> update(@Valid @RequestBody SetUpdateDTO updated) {
        return ResponseEntity.ok(ApiResponse.ok("Activity set updated successfully", activitySetService.update(updated)));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        activitySetService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Activity set deleted successfully"));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<SetDetailDTO>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Activity set detail fetched successfully", activitySetService.getDetail(id)));
    }

    @GetMapping("/summaries")
    public ResponseEntity<ApiResponse<List<SetSummary>>> listSummaries() {
        return ResponseEntity.ok(ApiResponse.ok("Activity set summaries fetched successfully", activitySetService.listSummaries()));
    }
}
