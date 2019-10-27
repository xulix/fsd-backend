package com.fsd.mod.user.repository;

import com.fsd.mod.user.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Page<Resource> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
