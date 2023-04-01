package com.oasis.repository;

import com.oasis.data.entity.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    List<StatisticsEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
