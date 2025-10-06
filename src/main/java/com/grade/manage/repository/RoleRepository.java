package com.grade.manage.repository;

import com.grade.manage.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNameIgnoreCase(String name);
}
