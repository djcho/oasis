package com.oasis.repository;

import com.oasis.data.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findAllByMemberSid(Long memberSid, Pageable pageable);
    Page<Schedule> findAll(Pageable pageable);
}
