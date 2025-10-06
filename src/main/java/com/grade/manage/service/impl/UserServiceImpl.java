package com.grade.manage.service.impl;

import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.detail.UserDetailDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.dto.update.PasswordChangeRequest;
import com.grade.manage.dto.update.ScoreAdjustmentRequest;
import com.grade.manage.dto.update.UserUpdateDTO;
import com.grade.manage.exception.EmailAlreadyExistsException;
import com.grade.manage.exception.InvalidCredentialsException;
import com.grade.manage.exception.UserNotFoundException;
import com.grade.manage.mapper.UserMapper;
import com.grade.manage.model.Role;
import com.grade.manage.model.ScoreHistory;
import com.grade.manage.model.User;
import com.grade.manage.repository.RoleRepository;
import com.grade.manage.repository.ScoreHistoryRepository;
import com.grade.manage.repository.UserRepository;
import com.grade.manage.service.UserService;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Studios TKOH!
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ScoreHistoryRepository scoreHistoryRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllDto() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findDtoById(Long id) {
        return userMapper.toDto(getUserOrThrow(id));
    }

    @Override
    @Transactional
    public UserDTO create(UserCreateDTO create) {
        if (userRepository.existsByEmailIgnoreCase(create.getEmail())) {
            throw new EmailAlreadyExistsException("Email '%s' is already registered".formatted(create.getEmail()));
        }

        User entity = userMapper.toEntity(create);
        entity.setName(create.getName());
        entity.setEmail(create.getEmail());
        entity.setPassword(passwordEncoder.encode(create.getPassword()));
        entity.setPasswordChangedAt(Instant.now());
        entity.setCurrentScore(0);
        entity.setEnabled(true);
        entity.setLocked(false);
        entity.setRoles(resolveRoles(create.getRoleIds()));

        User saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public UserDTO update(UserUpdateDTO update) {
        User entity = getUserOrThrow(update.getId());

        if (update.getEmail() != null && !update.getEmail().equalsIgnoreCase(entity.getEmail())
                && userRepository.existsByEmailIgnoreCase(update.getEmail())) {
            throw new EmailAlreadyExistsException("Email '%s' is already registered".formatted(update.getEmail()));
        }

        userMapper.updateEntity(update, entity);

        if (update.getEmail() != null) {
            entity.setEmail(update.getEmail());
        }
        if (update.getName() != null) {
            entity.setName(update.getName());
        }
        if (update.getPassword() != null) {
            entity.setPassword(passwordEncoder.encode(update.getPassword()));
            entity.setPasswordChangedAt(Instant.now());
        }
        if (update.getEnabled() != null) {
            entity.setEnabled(update.getEnabled());
        }
        if (update.getLocked() != null) {
            entity.setLocked(update.getLocked());
        }

        if (!CollectionUtils.isEmpty(update.getRoleIds())) {
            entity.setRoles(resolveRoles(update.getRoleIds()));
        }

        if (update.getCurrentScore() != null && !update.getCurrentScore().equals(entity.getCurrentScore())) {
            adjustScoreInternal(entity, update.getCurrentScore() - entity.getCurrentScore(),
                    LocalDateTime.now(), "Manual score override from user update");
        }

        User saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        User entity = getUserOrThrow(id);
        userRepository.delete(entity);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailDTO getDetail(Long id) {
        User user = userRepository.findDetailedById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id %d was not found".formatted(id)));
        return userMapper.toDetail(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listByRole(String roleName) {
        return userMapper.toDtoList(userRepository.findByRoles_NameIgnoreCase(roleName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> search(String query) {
        return userMapper.toDtoList(userRepository.search(query));
    }

    @Override
    @Transactional
    public void changePassword(Long id, PasswordChangeRequest request) {
        User user = getUserOrThrow(id);
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setPasswordChangedAt(Instant.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDTO adjustScore(Long id, ScoreAdjustmentRequest request) {
        User user = getUserOrThrow(id);
        adjustScoreInternal(user, request.getDelta(), request.getEffectiveAt(), request.getReason());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void lock(Long id) {
        User user = getUserOrThrow(id);
        user.setLocked(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void unlock(Long id) {
        User user = getUserOrThrow(id);
        user.setLocked(false);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '%s' not found".formatted(username)));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountLocked(user.getLocked())
                .disabled(!user.getEnabled())
                .build();
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id %d was not found".formatted(id)));
    }

    private Set<Role> resolveRoles(Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new HashSet<>();
        }

        List<Role> roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new IllegalArgumentException("One or more roles were not found");
        }
        return new HashSet<>(roles);
    }

    private void adjustScoreInternal(User user, Integer delta, LocalDateTime effectiveAt, String reason) {
        if (delta == null || delta == 0) {
            return;
        }

        int previousScore = user.getCurrentScore();
        int newScore = previousScore + delta;
        user.setCurrentScore(newScore);

        LocalDateTime effective = effectiveAt != null ? effectiveAt : resolveWeekStart(LocalDate.now());
        ScoreHistory history = ScoreHistory.builder()
                .weekStart(effective)
                .previousScore(previousScore)
                .newScore(newScore)
                .delta(BigDecimal.valueOf(delta))
                .reason(reason)
                .user(user)
                .build();
        scoreHistoryRepository.save(history);
        user.getScores().add(history);
    }

    private LocalDateTime resolveWeekStart(LocalDate date) {
        LocalDate monday = date.with(DayOfWeek.MONDAY);
        return monday.atStartOfDay();
    }
}
