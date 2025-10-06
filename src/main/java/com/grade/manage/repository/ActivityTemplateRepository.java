package com.grade.manage.repository;

import com.grade.manage.model.ActivityTemplate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface ActivityTemplateRepository extends JpaRepository<ActivityTemplate, Long> {

    List<ActivityTemplate> findByActiveTrue();

    List<ActivityTemplate> findByActiveTrueAndWeekday(Integer weekday);
}
