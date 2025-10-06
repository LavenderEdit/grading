package com.grade.manage.controller;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.summary.TemplateSummary;
import com.grade.manage.dto.update.TempAssignUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.ActivityTemplateService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Studios TKOH!
 */
@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
@Validated
public class ActivityTemplateController {

    private final ActivityTemplateService activityTemplateService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TemplateSummary>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Templates fetched successfully", activityTemplateService.listAll()));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<TemplateSummary>>> listActive() {
        return ResponseEntity.ok(ApiResponse.ok("Active templates fetched successfully", activityTemplateService.listActive()));
    }

    @GetMapping("/active/by-weekday")
    public ResponseEntity<ApiResponse<List<TemplateSummary>>> listActiveByWeekday(@RequestParam Integer weekday) {
        return ResponseEntity.ok(ApiResponse.ok("Templates fetched successfully", activityTemplateService.listActiveByWeekday(weekday)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TemplateSummary>> getSummary(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Template fetched successfully", activityTemplateService.getSummary(id)));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Template detail fetched successfully", activityTemplateService.getDetail(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> create(@Valid @RequestBody TempAssignCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("Template created successfully", activityTemplateService.create(create)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> update(@Valid @RequestBody TempAssignUpdateDTO update) {
        return ResponseEntity.ok(ApiResponse.ok("Template updated successfully", activityTemplateService.update(update)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        activityTemplateService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Template deleted successfully"));
    }
}
