package com.fsd.mod.training.repository;

import com.fsd.mod.training.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}
