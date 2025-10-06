package com.grade.manage.mapper;

import com.grade.manage.dto.create.SetCreateDTO;
import com.grade.manage.dto.detail.SetDetailDTO;
import com.grade.manage.dto.simple.SetDTO;
import com.grade.manage.dto.summary.SetSummary;
import com.grade.manage.dto.update.SetUpdateDTO;
import com.grade.manage.model.ActivitySet;
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
@Mapper(componentModel = "spring", uses = TempAssignMapper.class)
public interface SetMapper {

    @Mapping(target = "assignmentCount", expression = "java(set.getRelations().size())")
    SetDTO toDto(ActivitySet set);

    List<SetDTO> toDtoList(Collection<ActivitySet> sets);

    @Mapping(target = "assignments", source = "relations")
    @Mapping(target = "assignmentCount", expression = "java(set.getRelations().size())")
    SetDetailDTO toDetail(ActivitySet set);

    SetSummary toSummary(ActivitySet set);

    List<SetSummary> toSummaryList(Collection<ActivitySet> sets);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relations", ignore = true)
    ActivitySet toEntity(SetCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "relations", ignore = true)
    void updateEntity(SetUpdateDTO dto, @MappingTarget ActivitySet set);
}
