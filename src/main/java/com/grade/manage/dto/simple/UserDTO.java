package com.grade.manage.dto.simple;

import com.grade.manage.dto.summary.RoleSummary;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Lavender
 */
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Integer current_score;
    private List<RoleSummary> roles;
}
