package com.grade.manage.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class UserUpdateDTO {

    @NotNull
    private Long id;

    @Size(max = 100)
    private String name;

    @Email
    @Size(max = 60)
    private String email;
    private Integer currentScore;
    private Set<Long> roleIds;
    private Boolean enabled;
    private Boolean locked;

    @Size(min = 8, max = 72)
    private String password;
}
