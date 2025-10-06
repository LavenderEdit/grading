package com.grade.manage.controller;

import com.grade.manage.controller.generic.DtoCrudController;
import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.detail.UserDetailDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.dto.update.PasswordChangeRequest;
import com.grade.manage.dto.update.ScoreAdjustmentRequest;
import com.grade.manage.dto.update.UserUpdateDTO;
import com.grade.manage.response.ApiResponse;
import com.grade.manage.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Studios TKOH!
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController implements DtoCrudController<UserDTO, UserCreateDTO, UserUpdateDTO, Long> {

    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponse<List<UserDTO>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok("Users fetched successfully", userService.findAllDto()));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("User fetched successfully", userService.findDtoById(id)));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDTO>> create(@Valid @RequestBody UserCreateDTO create) {
        return ResponseEntity.ok(ApiResponse.ok("User created successfully", userService.create(create)));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDTO>> update(@Valid @RequestBody UserUpdateDTO updated) {
        return ResponseEntity.ok(ApiResponse.ok("User updated successfully", userService.update(updated)));
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("User deleted successfully"));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<UserDetailDTO>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("User detail fetched successfully", userService.getDetail(id)));
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity<ApiResponse<List<UserDTO>>> listByRole(@PathVariable String role) {
        return ResponseEntity.ok(ApiResponse.ok("Users filtered by role", userService.listByRole(role)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserDTO>>> search(@RequestParam("q") String query) {
        return ResponseEntity.ok(ApiResponse.ok("Users filtered by query", userService.search(query)));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@PathVariable Long id,
            @Valid @RequestBody PasswordChangeRequest request) {
        userService.changePassword(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Password updated successfully"));
    }

    @PostMapping("/{id}/score-adjustments")
    public ResponseEntity<ApiResponse<UserDTO>> adjustScore(@PathVariable Long id,
            @Valid @RequestBody ScoreAdjustmentRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Score adjusted successfully", userService.adjustScore(id, request)));
    }

    @PostMapping("/{id}/lock")
    public ResponseEntity<ApiResponse<Void>> lock(@PathVariable Long id) {
        userService.lock(id);
        return ResponseEntity.ok(ApiResponse.ok("User locked successfully"));
    }

    @PostMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<Void>> unlock(@PathVariable Long id) {
        userService.unlock(id);
        return ResponseEntity.ok(ApiResponse.ok("User unlocked successfully"));
    }
}
