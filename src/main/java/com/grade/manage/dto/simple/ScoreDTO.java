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
    private LocalDateTime week_start;
    private Integer previous_score;
    private Integer new_score;
    private Long user_id;
}
