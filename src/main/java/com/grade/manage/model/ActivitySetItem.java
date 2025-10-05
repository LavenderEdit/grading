package com.grade.manage.model;

import com.grade.manage.audit.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = "activity_set_items", schema = "gradetracker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivitySetItem extends BaseEntity implements Serializable {

    @EmbeddedId
    private ActivitySetItemId id;

    @Builder.Default
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 1;

    //Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("setId")
    @JoinColumn(name = "set_id", nullable = false)
    private ActivitySet set;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("templateId")
    @JoinColumn(name = "template_id", nullable = false)
    private ActivityTemplate template;
}
