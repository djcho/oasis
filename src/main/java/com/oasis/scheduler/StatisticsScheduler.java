package com.oasis.scheduler;

import com.oasis.service.StatisticsService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class StatisticsScheduler {
    private final StatisticsService statisticsService;

    public void doFirstDayOfMonth() {

    }

}
