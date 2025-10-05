package com.grade.manage.dto.create;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class TempAssignCreateDTO { //When an assignment is created, a template is created along with it, so both are inseparable

    // Template
    private String name;
    private Integer weekday; //It autocompletes with the LocalDateTime it takes from assignment
    private Integer weight; //ComboBox on Front
    private String description; //Optional
    private Long setId; //When creating the template it gets assigned the set it comes from

    // Assignment
    private LocalDateTime date;
    private Long user_id; //Validation on the service so that the user assigned to is not an Evaluator
}
