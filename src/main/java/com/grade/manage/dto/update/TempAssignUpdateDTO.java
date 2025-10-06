package com.grade.manage.dto.update;

import com.grade.manage.util.Enums.GradeStatus;
import com.grade.manage.util.Enums.Status;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class TempAssignUpdateDTO {

    // Template & Assignment Ids
    private Long templateId;
    private Long assignmentId;

    // Template Updatable
    private String name;
    private Integer weekday; //It autocompletes with the LocalDateTime it takes from assignment
    private Integer weight; //ComboBox on Front
    private String description; //Optional

    // Assignment Updatable
    private LocalDateTime date;
    private Long userId; //Validation on the service so that the user assigned to is not an Evaluator
    private Status status;
    private GradeStatus gradeStatus;
    private String evidenceUrl;
}
