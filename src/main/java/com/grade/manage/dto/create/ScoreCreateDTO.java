package com.grade.manage.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreCreateDTO {

    @NotNull
    private LocalDateTime weekStart;

    @NotNull
    private Integer previousScore;

    @NotNull
    private Integer newScore;

    @NotNull
    private BigDecimal delta;

    private String reason;

    @NotNull
    @Positive
    private Long userId;
}
