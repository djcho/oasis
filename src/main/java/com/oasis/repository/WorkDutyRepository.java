package com.oasis.repository;

import com.oasis.data.entity.WorkDutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDutyRepository extends JpaRepository<WorkDutyEntity, Long> {
}
