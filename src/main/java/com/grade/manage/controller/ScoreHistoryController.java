package com.grade.manage.controller;

import com.grade.manage.controller.generic.DtoCrudController;
import com.grade.manage.dto.create.ScoreCreateDTO;
import com.grade.manage.dto.detail.ScoreDetailDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.ScoreHistorySummary;
import com.grade.manage.dto.update.ScoreUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.ScoreHistoryService;
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
@RequestMapping("/api/score-history")
@RequiredArgsConstructor
@Validated
public class ScoreHistoryController implements DtoCrudController<ScoreDTO, ScoreCreateDTO, ScoreUpdateDTO, Long> {

    private final ScoreHistoryService scoreHistoryService;

    @Override
    public ResponseEntity<ApiResponse<List<ScoreDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Score history fetched successfully", scoreHistoryService.findAllDto()));
    }

    @Override
    public ResponseEntity<ApiResponse<ScoreDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Score entry fetched successfully", scoreHistoryService.findDtoById(id)));
    }

    @Override
    public ResponseEntity<ApiResponse<ScoreDTO>> create(@Valid @RequestBody ScoreCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("Score entry created successfully", scoreHistoryService.create(create)));
    }

    @Override
    public ResponseEntity<ApiResponse<ScoreDTO>> update(@Valid @RequestBody ScoreUpdateDTO updated) {
        return ResponseEntity.ok(ApiResponse.ok("Score entry updated successfully", scoreHistoryService.update(updated)));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        scoreHistoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Score entry deleted successfully"));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<ScoreDetailDTO>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Score entry detail fetched successfully", scoreHistoryService.getDetail(id)));
    }

    @GetMapping("/users/{userId}/recent")
    public ResponseEntity<ApiResponse<List<ScoreHistorySummary>>> listRecentSummaries(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.ok("Recent score history fetched successfully", scoreHistoryService.listRecentSummaries(userId)));
    }
}
