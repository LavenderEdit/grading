package com.grade.manage.model;

import com.grade.manage.audit.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
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
@Table(name = "users", schema = "gradetracker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Email
    @Column(name = "email", length = 60, nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Builder.Default
    @Column(name = "current_score")
    private Integer currentScore = 0;
    
    @Column(name = "password_changed_at")
    private Instant passwordChangedAt;

    @Builder.Default
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @Builder.Default
    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    //Relations
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Assignment> assignments = new LinkedHashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScoreHistory> scores = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol",
            schema = "gradetracker",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
