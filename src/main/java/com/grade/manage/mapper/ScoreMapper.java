package com.grade.manage.mapper;

import com.grade.manage.dto.create.ScoreCreateDTO;
import com.grade.manage.dto.detail.ScoreDetailDTO;
import com.grade.manage.dto.simple.ScoreDTO;
import com.grade.manage.dto.summary.ScoreHistorySummary;
import com.grade.manage.dto.update.ScoreUpdateDTO;
import com.grade.manage.model.ScoreHistory;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Studios TKOH!
 */
@Mapper(componentModel = "spring")
public interface ScoreMapper {

    @Mapping(source = "user.id", target = "user_id")
    ScoreDTO toDTO(ScoreHistory sh);

    List<ScoreDTO> toDTOList(List<ScoreHistory> shs);

    @Mapping(source = "user.id", target = "user_id")
    ScoreDetailDTO toDetailDTO(ScoreHistory sh);

    List<ScoreDetailDTO> toDetailDTOList(List<ScoreHistory> shs);

    // SUMMARY
    ScoreHistorySummary toSummaryDTO(ScoreHistory sh);

    List<ScoreHistorySummary> toSummaryDTOList(List<ScoreHistory> shs);

    // FOR CREATION
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "user", ignore = true)
    })
    ScoreHistory toEntityFromCreate(ScoreCreateDTO create);

    // FOR UPDATING
    @Mapping(target = "user", ignore = true)
    ScoreHistory toEntityFromCreate(ScoreUpdateDTO update);
}
