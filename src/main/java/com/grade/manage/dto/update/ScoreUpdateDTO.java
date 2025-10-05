package com.grade.manage.dto.update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreUpdateDTO {

    private Long id;
    private LocalDateTime week_start;
    private Integer previous_score;
    private Integer new_score;
    private BigDecimal delta;
    private String reason;
}
