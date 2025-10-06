package com.grade.manage.mapper;

import com.grade.manage.dto.create.ScoreCreateDTO;
import com.grade.manage.dto.detail.ScoreDetailDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.ScoreHistorySummary;
import com.grade.manage.dto.update.ScoreUpdateDTO;
import com.grade.manage.model.ScoreHistory;
import java.util.Collection;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 *
 * @author Studios TKOH!
 */
@Mapper(componentModel = "spring")
public interface ScoreMapper {

    @Mapping(source = "user.id", target = "userId")
    ScoreDTO toDTO(ScoreHistory sh);

    List<ScoreDTO> toDTOList(Collection<ScoreHistory> shs);

    @Mapping(source = "user.id", target = "userId")
    ScoreDetailDTO toDetailDTO(ScoreHistory sh);

    List<ScoreDetailDTO> toDetailDTOList(Collection<ScoreHistory> shs);

    // SUMMARY
    ScoreHistorySummary toSummaryDTO(ScoreHistory sh);

    List<ScoreHistorySummary> toSummaryDTOList(Collection<ScoreHistory> shs);

    // FOR CREATION
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "user", ignore = true)
    })
    ScoreHistory toEntityFromCreate(ScoreCreateDTO create);

    // FOR UPDATING
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", ignore = true)
    void updateEntity(ScoreUpdateDTO dto, @MappingTarget ScoreHistory entity);
}
