package com.oasis.repository;

import com.oasis.data.entity.WorkPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPositionRepository extends JpaRepository<WorkPositionEntity, Long> {
}
