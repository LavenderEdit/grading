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
public class AssignmentUpdateDTO {

    private Long id;
    private LocalDateTime date;
    private Status status;
    private GradeStatus grade_status;
    private String evidence_url;
    private Long user_id; //Validation on the service so that the user assigned to is not an Evaluator
    private TempAssignUpdateDTO template; //When an assignment is update, the template assigned to it is updated with it if needed
}
