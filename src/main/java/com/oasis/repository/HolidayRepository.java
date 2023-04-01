package com.oasis.repository;

import com.oasis.data.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
}
