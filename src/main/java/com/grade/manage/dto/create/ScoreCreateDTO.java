package com.grade.manage.dto.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreCreateDTO {

    private LocalDateTime week_start;
    private Integer previous_score;
    private Integer new_score;
    private BigDecimal delta;
    private String reason;
    private Long usuario_id;
}
