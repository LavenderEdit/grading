package com.grade.manage.repository;

import com.grade.manage.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRoles_NameIgnoreCase(String roleName);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE lower(u.name) LIKE lower(concat('%', :query, '%'))" +
            " OR lower(u.email) LIKE lower(concat('%', :query, '%'))")
    List<User> search(@Param("query") String query);

    @EntityGraph(attributePaths = {"assignments", "assignments.template", "scores", "roles"})
    Optional<User> findDetailedById(Long id);
}