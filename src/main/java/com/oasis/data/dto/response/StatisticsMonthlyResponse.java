package com.oasis.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsMonthlyResponse {
    private int year;
    private int month;
    private long workingDayCount;
    private long officeWorkCount;
    private long remoteWorkCount;
    private long dayOffCount;
    private long halfDayOffCount;
    private double officeWorkRate;
    private double remoteWorkRate;
    private double dayOffRate;

}
