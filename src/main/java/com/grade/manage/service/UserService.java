package com.grade.manage.service;

import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.detail.UserDetailDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.dto.update.PasswordChangeRequest;
import com.grade.manage.dto.update.ScoreAdjustmentRequest;
import com.grade.manage.dto.update.UserUpdateDTO;
import com.grade.manage.service.generic.DtoCrudService;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Studios TKOH!
 */
public interface UserService extends DtoCrudService<UserDTO, UserCreateDTO, UserUpdateDTO, Long>, UserDetailsService {

    UserDetailDTO getDetail(Long id);

    List<UserDTO> listByRole(String roleName);

    List<UserDTO> search(String query);

    void changePassword(Long id, PasswordChangeRequest request);

    UserDTO adjustScore(Long id, ScoreAdjustmentRequest request);

    void lock(Long id);

    void unlock(Long id);
}
