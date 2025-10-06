package com.grade.manage.repository;

import com.grade.manage.model.ScoreHistory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface ScoreHistoryRepository extends JpaRepository<ScoreHistory, Long> {

    List<ScoreHistory> findTop10ByUser_IdOrderByWeekStartDesc(Long userId);

    boolean existsByUser_IdAndWeekStart(Long userId, LocalDateTime weekStart);
}
