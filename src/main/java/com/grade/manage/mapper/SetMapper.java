package com.grade.manage.mapper;

import com.grade.manage.dto.detail.SetDetailDTO;
import com.grade.manage.dto.simple.SetDTO;
import com.grade.manage.model.ActivitySet;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}
