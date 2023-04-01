package com.oasis.repository;

import com.oasis.data.entity.WorkLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLogEntity, Long> {
}
