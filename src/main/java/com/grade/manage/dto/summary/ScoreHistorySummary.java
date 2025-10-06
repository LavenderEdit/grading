package com.grade.manage.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Studios TKOH!
 */
public record ScoreHistorySummary(
        Long id,
        LocalDateTime weekStart,
        Integer previousScore,
        BigDecimal delta,
        String reason) {

}
