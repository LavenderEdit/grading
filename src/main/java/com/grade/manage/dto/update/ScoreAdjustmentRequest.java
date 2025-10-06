package com.grade.manage.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreAdjustmentRequest {
    
    @NotNull
    private Integer delta;

    private LocalDateTime effectiveAt;

    @NotBlank
    @Size(max = 500)
    private String reason;
}
