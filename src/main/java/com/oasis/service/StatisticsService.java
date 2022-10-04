package com.oasis.service;

import com.oasis.data.dto.response.StatisticsMonthlyResponse;

import java.util.List;

public interface StatisticsService {
    public List<StatisticsMonthlyResponse> monthly(int year);
}
