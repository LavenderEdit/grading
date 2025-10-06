package com.grade.manage.dto.update;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreUpdateDTO {

    @NotNull
    private Long id;
    private LocalDateTime weekStart;
    private Integer previousScore;
    private Integer newScore;
    private BigDecimal delta;
    private String reason;
}
