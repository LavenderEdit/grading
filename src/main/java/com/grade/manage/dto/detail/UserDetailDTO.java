package com.grade.manage.dto.detail;

import com.grade.manage.dto.simple.TempAssignDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.RoleSummary;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Lavender
 */
@Data
public class UserDetailDTO {

    private Long id;
    private String name;
    private String email;
    private Integer currentScore;
    private Boolean enabled;
    private Boolean locked;
    private List<TempAssignDTO> assignments;
    private List<ScoreDTO> scores;
    private List<RoleSummary> roles;
}
