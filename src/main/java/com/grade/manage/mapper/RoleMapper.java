package com.grade.manage.mapper;

import com.grade.manage.dto.create.RoleCreateDTO;
import com.grade.manage.dto.detail.RoleDetailDTO;
import com.grade.manage.dto.summary.RoleSummary;
import com.grade.manage.dto.summary.UserSummary;
import com.grade.manage.dto.update.RoleUpdateDTO;
import com.grade.manage.model.Role;
import com.grade.manage.model.User;
import java.util.Collection;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 *
 * @author Studios TKOH!
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleSummary toSummary(Role role);

    List<RoleSummary> toSummaryList(Collection<Role> roles);

    @Mapping(target = "users", source = "users")
    RoleDetailDTO toDetail(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "users", ignore = true)
    void updateEntity(RoleUpdateDTO dto, @MappingTarget Role role);

    UserSummary toUserSummary(User user);
}
