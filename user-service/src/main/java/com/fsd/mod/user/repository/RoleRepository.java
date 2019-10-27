package com.fsd.mod.user.repository;

import com.fsd.mod.user.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
