package com.grade.manage.repository;

import com.grade.manage.model.ActivitySet;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface ActivitySetRepository extends JpaRepository<ActivitySet, Long> {

    @EntityGraph(attributePaths = {"relations", "relations.template"})
    Optional<ActivitySet> findWithTemplatesById(Long id);
}
