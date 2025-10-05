package com.grade.manage.dto.simple;

import com.grade.manage.util.Enums;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Studios TKOH!
 */
@Data
public class TempAssignDTO {

    // Template & Assignment Ids
    private Long templateId;
    private Long assignmentId;

    // Template Showable
    private String name;
    private Integer weight; //ComboBox on Front

    // Assignment Showable
    private LocalDateTime date;
    private Long user_id; //Validation on the service so that the user assigned to is not an Evaluator
    private Enums.Status status;
    private Enums.GradeStatus grade_status;
}
