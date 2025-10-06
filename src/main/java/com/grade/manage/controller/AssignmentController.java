package com.grade.manage.controller;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.simple.TempAssignDTO;
import com.grade.manage.dto.update.AssignmentUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.AssignmentService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@Validated
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TempAssignDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Assignments fetched successfully", assignmentService.listAll()));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<TempAssignDTO>>> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.ok("Assignments fetched successfully", assignmentService.listByUser(userId)));
    }

    @GetMapping("/range")
    public ResponseEntity<ApiResponse<List<TempAssignDTO>>> listByRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(ApiResponse.ok("Assignments fetched successfully", assignmentService.listByDateRange(start, end)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Assignment fetched successfully", assignmentService.getDetail(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> create(@Valid @RequestBody TempAssignCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("Assignment created successfully", assignmentService.create(create)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TempAssignDetailDTO>> update(@Valid @RequestBody AssignmentUpdateDTO update) {
        return ResponseEntity.ok(ApiResponse.ok("Assignment updated successfully", assignmentService.update(update)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Assignment deleted successfully"));
    }
}
