package com.oasis.service;

import com.oasis.data.entity.WorkDutyEntity;

public interface WorkDutyService {
    
    void createWorkDuty(WorkDutyEntity workDutyEntity);
    WorkDutyEntity getWorkDuty(long id);
}
