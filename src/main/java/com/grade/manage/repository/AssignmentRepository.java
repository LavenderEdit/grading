package com.grade.manage.repository;

import com.grade.manage.model.Assignment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @EntityGraph(attributePaths = {"template", "user"})
    List<Assignment> findByUser_IdOrderByDateDesc(Long userId);

    @EntityGraph(attributePaths = {"template", "user"})
    List<Assignment> findByDateBetween(LocalDateTime start, LocalDateTime end);

    // Ajusta los attributePaths a los nombres REALES en tu entity Assignment
    @EntityGraph(attributePaths = {"student", "activityTemplate", "grades"})
    List<Assignment> findAllBy();

    @EntityGraph(attributePaths = {"student", "activityTemplate", "grades"})
    Optional<Assignment> findWithDetailsById(Long id); // válido porque tiene ById
}
