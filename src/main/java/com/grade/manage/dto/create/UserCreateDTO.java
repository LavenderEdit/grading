package com.grade.manage.dto.create;

import lombok.Data;

/**
 *
 * @author Lavender
 */
@Data
public class UserCreateDTO {

    private String name;
    private String email;
    private Long role_id;
}
