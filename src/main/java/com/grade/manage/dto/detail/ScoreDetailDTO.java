package com.grade.manage.dto.detail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreDetailDTO {

    private Long id;
    private LocalDateTime weekStart;
    private Integer previousScore;
    private Integer newScore;
    private BigDecimal delta;
    private String reason;
    private Long userId;
}
