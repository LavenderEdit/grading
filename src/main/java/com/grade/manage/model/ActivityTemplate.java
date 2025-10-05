package com.grade.manage.model;

import com.grade.manage.audit.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
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
@Table(name = "activity_templates", schema = "gradetracker",
        indexes = {
            @Index(name = "idx_tpl_weekday_active", columnList = "weekday,active")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityTemplate extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Min(1)
    @Max(7)
    @Column(name = "weekday", nullable = false)
    private Integer weekday; // 1 - 7

    @Min(1)
    @Max(3)
    @Column(name = "weight", nullable = false)
    private Integer weight; // 1 - 3 (Facil - Medio - Dificil)

    @Lob
    @Column(name = "description")
    private String description;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    //Relations
    @Builder.Default
    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ActivitySetItem> relations = new LinkedHashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Assignment> assignments = new LinkedHashSet<>();
}
