package com.fsd.mod.training.repository;

import com.fsd.mod.training.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    Page<Training> findAllByStatusIn(Pageable pageable, String... statuses);

}
