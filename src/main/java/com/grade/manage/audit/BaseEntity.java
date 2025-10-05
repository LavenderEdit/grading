package com.grade.manage.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Studios TKOH!
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 150)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected Instant createdAt;

    @LastModifiedBy
    @Column(name = "modified_by", length = 150)
    protected String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Version
    @Column(name = "version")
    protected Long version;
}
