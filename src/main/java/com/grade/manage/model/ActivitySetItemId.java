package com.grade.manage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Studios TKOH!
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ActivitySetItemId implements Serializable {

    @Column(name = "set_id", nullable = false)
    private Long setId;

    @Column(name = "template_id", nullable = false)
    private Long templateId;
}
