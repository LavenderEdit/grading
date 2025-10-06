package com.grade.manage.mapper;

import com.grade.manage.dto.create.UserCreateDTO;
import com.grade.manage.dto.detail.UserDetailDTO;
import com.grade.manage.dto.simple.UserDTO;
import com.grade.manage.dto.update.UserUpdateDTO;
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
@Mapper(componentModel = "spring", uses = {RoleMapper.class, ScoreMapper.class, TempAssignMapper.class})
public interface UserMapper {

    @Mapping(target = "currentScore", source = "currentScore")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "enabled", source = "enabled")
    @Mapping(target = "locked", source = "locked")
    UserDTO toDto(User user);

    List<UserDTO> toDtoList(Collection<User> users);

    @Mapping(target = "currentScore", source = "currentScore")
    @Mapping(target = "assignments", source = "assignments")
    @Mapping(target = "scores", source = "scores")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "enabled", source = "enabled")
    @Mapping(target = "locked", source = "locked")
    UserDetailDTO toDetail(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "scores", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "passwordChangedAt", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "currentScore", ignore = true)
    User toEntity(UserCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "scores", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntity(UserUpdateDTO dto, @MappingTarget User user);
}
