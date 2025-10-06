package com.grade.manage.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Lavender
 */
@Data
public class UserCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;

    @NotEmpty
    private Set<Long> roleIds;
}
