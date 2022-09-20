package com.oasis.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String date;

    private long officeWorkCount;
    private long remoteWorkCount;
    private long dayOffCount;
    private long halfDayOffCount;
    private double officeWorkRate;
    private double remoteWorkRate;
    private double dayOffRate;

    private Long userSid;
}
