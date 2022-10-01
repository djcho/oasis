package com.oasis.repository;

import com.oasis.data.entity.WorkPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPositionRepository extends JpaRepository<WorkPosition, Long> {
}
