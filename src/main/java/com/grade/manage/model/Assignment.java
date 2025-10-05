package com.grade.manage.model;

import com.grade.manage.audit.BaseEntity;
import com.grade.manage.util.Enums.GradeStatus;
import com.grade.manage.util.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Studios TKOH!
 */
@Entity
@Table(name = "assignments", schema = "gradetracker", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private Status status = Status.pending;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_status", length = 10)
    private GradeStatus grade_status;

    @Column(name = "evidence_url", length = 60)
    private String evidence_url;

    //Relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ActivityTemplate template;
}
