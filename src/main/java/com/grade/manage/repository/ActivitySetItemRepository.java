package com.grade.manage.repository;

import com.grade.manage.model.ActivitySetItem;
import com.grade.manage.model.ActivitySetItemId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface ActivitySetItemRepository extends JpaRepository<ActivitySetItem, ActivitySetItemId> {

    List<ActivitySetItem> findBySet_IdOrderBySortOrderAsc(Long setId);
}
