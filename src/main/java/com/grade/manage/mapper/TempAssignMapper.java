package com.grade.manage.mapper;

import com.grade.manage.dto.create.TempAssignCreateDTO;
import com.grade.manage.dto.detail.TempAssignDetailDTO;
import com.grade.manage.dto.simple.TempAssignDTO;
import com.grade.manage.dto.update.TempAssignUpdateDTO;
import com.grade.manage.model.ActivitySetItem;
import com.grade.manage.model.ActivityTemplate;
import com.grade.manage.model.Assignment;
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
public interface TempAssignMapper {

    @Mapping(target = "templateId", source = "template.id")
    @Mapping(target = "assignmentId", source = "id")
    @Mapping(target = "name", source = "template.name")
    @Mapping(target = "weight", source = "template.weight")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "gradeStatus", source = "gradeStatus")
    TempAssignDTO toDto(Assignment assignment);

    List<TempAssignDTO> toDtoList(Collection<Assignment> assignments);

    @Mapping(target = "templateId", source = "template.id")
    @Mapping(target = "assignmentId", source = "id")
    @Mapping(target = "name", source = "template.name")
    @Mapping(target = "weekday", source = "template.weekday")
    @Mapping(target = "weight", source = "template.weight")
    @Mapping(target = "description", source = "template.description")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "gradeStatus", source = "gradeStatus")
    @Mapping(target = "evidenceUrl", source = "evidenceUrl")
    TempAssignDetailDTO toDetail(Assignment assignment);

    @Mapping(target = "templateId", source = "template.id")
    @Mapping(target = "assignmentId", ignore = true)
    @Mapping(target = "name", source = "template.name")
    @Mapping(target = "weight", source = "template.weight")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "gradeStatus", ignore = true)
    TempAssignDTO toDto(ActivitySetItem relation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relations", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    ActivityTemplate toTemplate(TempAssignCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.grade.manage.util.Enums.Status.pending)")
    @Mapping(target = "gradeStatus", ignore = true)
    @Mapping(target = "evidenceUrl", ignore = true)
    @Mapping(target = "template", ignore = true)
    @Mapping(target = "user", ignore = true)
    Assignment toAssignment(TempAssignCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "relations", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    void updateTemplate(TempAssignUpdateDTO dto, @MappingTarget ActivityTemplate template);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "template", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateAssignment(TempAssignUpdateDTO dto, @MappingTarget Assignment assignment);
}
