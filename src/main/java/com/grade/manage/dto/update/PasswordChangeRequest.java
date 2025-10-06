package com.grade.manage.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class PasswordChangeRequest {

    @NotBlank
    @Size(min = 8, max = 72)
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 72)
    private String newPassword;
}
