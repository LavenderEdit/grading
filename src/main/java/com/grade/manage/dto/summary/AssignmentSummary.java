package com.grade.manage.dto.summary;

import com.grade.manage.util.Enums.GradeStatus;
import com.grade.manage.util.Enums.Status;
import java.time.LocalDateTime;

/**
 *
 * @author Studios TKOH!
 */
public record AssignmentSummary(
        Long id,
        LocalDateTime date,
        Status status,
        GradeStatus gradeStatus,
        String evidenceUrl) {

}
