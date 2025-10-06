package com.grade.manage.model;

import com.grade.manage.audit.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "score_history", schema = "gradetracker", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "week_start"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreHistory extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "week_start", nullable = false)
    private LocalDateTime weekStart;

    @NotNull
    @Column(name = "previous_score", nullable = false)
    private Integer previousScore;

    @NotNull
    @Column(name = "new_score", nullable = false)
    private Integer newScore;

    @NotNull
    @Column(name = "delta", precision = 6, scale = 2, nullable = false)
    private BigDecimal delta;

    @Lob
    @Column(name = "reason")
    private String reason;

    //Relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
