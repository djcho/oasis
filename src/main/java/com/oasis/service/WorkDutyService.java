package com.oasis.service;

import com.oasis.data.entity.WorkDuty;

public interface WorkDutyService {
    
    void createWorkDuty(WorkDuty workDuty);
    WorkDuty getWorkDuty(long sid);
}
