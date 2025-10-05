package com.grade.manage.dto.detail;

import com.grade.manage.dto.summary.UserSummary;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Lavender
 */
@Data
public class RoleDetailDTO {

    private Long id;
    private String name;
    private List<UserSummary> users;
}
