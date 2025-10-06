package com.grade.manage.service.impl;

import com.grade.manage.dto.create.RoleCreateDTO;
import com.grade.manage.dto.detail.RoleDetailDTO;
import com.grade.manage.dto.summary.RoleSummary;
import com.grade.manage.dto.update.RoleUpdateDTO;
import com.grade.manage.exception.RoleNotFoundException;
import com.grade.manage.mapper.RoleMapper;
import com.grade.manage.model.Role;
import com.grade.manage.repository.RoleRepository;
import com.grade.manage.service.RoleService;
import jakarta.persistence.EntityExistsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Studios TKOH!
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RoleDetailDTO> findAllDto() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDetail)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDetailDTO findDtoById(Long id) {
        return roleMapper.toDetail(getRoleOrThrow(id));
    }

    @Override
    @Transactional
    public RoleDetailDTO create(RoleCreateDTO create) {
        roleRepository.findByNameIgnoreCase(create.getName()).ifPresent(role -> {
            throw new EntityExistsException("Role with name '%s' already exists".formatted(create.getName()));
        });

        Role entity = roleMapper.toEntity(create);
        Role saved = roleRepository.save(entity);
        return roleMapper.toDetail(saved);
    }

    @Override
    @Transactional
    public RoleDetailDTO update(RoleUpdateDTO update) {
        Role entity = getRoleOrThrow(update.getId());
        roleMapper.updateEntity(update, entity);
        return roleMapper.toDetail(roleRepository.save(entity));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Role entity = getRoleOrThrow(id);
        if (!entity.getUsers().isEmpty()) {
            throw new IllegalStateException("Cannot delete role because it is assigned to active users");
        }
        roleRepository.delete(entity);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleSummary getSummaryByName(String name) {
        Role role = roleRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RoleNotFoundException("Role '%s' not found".formatted(name)));
        return roleMapper.toSummary(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleSummary> listSummaries() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toSummary)
                .toList();
    }

    private Role getRoleOrThrow(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role with id %d was not found".formatted(id)));
    }
}
