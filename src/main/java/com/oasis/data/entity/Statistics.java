package com.oasis.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private LocalDate date;

    private long workingDayCount;

    private long officeWorkCount;
    private long remoteWorkCount;
    private long dayOffCount;
    private long halfDayOffCount;
    private double officeWorkRate;
    private double remoteWorkRate;
    private double dayOffRate;

    private Long userSid;
}
