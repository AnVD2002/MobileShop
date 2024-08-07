package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Query("select r from Roles r where r.roleName=:role")
    Optional<Roles> findByName(@Param("role") String role);
}
