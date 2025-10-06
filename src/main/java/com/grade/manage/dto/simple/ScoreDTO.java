package com.grade.manage.dto.simple;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class ScoreDTO {
    
    private Long id;
    private LocalDateTime weekStart;
    private Integer previousScore;
    private Integer newScore;
    private Long userId;
}
