package com.grade.manage.dto.update;

import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class UserUpdateDTO {

    private Long id;
    private String name;
    private String email;
    private Integer current_score;
    private Long role_id;
}
