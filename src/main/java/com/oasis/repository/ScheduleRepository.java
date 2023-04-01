package com.oasis.repository;

import com.oasis.data.entity.ScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    Page<ScheduleEntity> findAllByMemberId(Long memberId, Pageable pageable);
    Page<ScheduleEntity> findAll(Pageable pageable);
}
