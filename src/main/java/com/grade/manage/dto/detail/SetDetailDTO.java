package com.grade.manage.dto.detail;

import com.grade.manage.dto.simple.TempAssignDTO;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class SetDetailDTO {

    private Long id;
    private String name;
    private String description;
    private List<TempAssignDTO> tempassigns;
    private Integer countTempAssigns;
}
