package com.oasis.repository;

import com.oasis.data.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
}
