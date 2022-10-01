package com.oasis.repository;

import com.oasis.data.entity.WorkDuty;
import com.oasis.data.entity.WorkPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDutyRepository extends JpaRepository<WorkDuty, Long> {
}
