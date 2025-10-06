package com.grade.manage.service;

import com.grade.manage.dto.create.ScoreCreateDTO;
import com.grade.manage.dto.detail.ScoreDetailDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.ScoreHistorySummary;
import com.grade.manage.dto.update.ScoreUpdateDTO;
import com.grade.manage.service.generic.DtoCrudService;
import java.util.List;

/**
 *
 * @author Studios TKOH!
 */
public interface ScoreHistoryService extends DtoCrudService<ScoreDTO, ScoreCreateDTO, ScoreUpdateDTO, Long> {

    ScoreDetailDTO getDetail(Long id);

    List<ScoreHistorySummary> listRecentSummaries(Long userId);
}
