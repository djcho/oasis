package com.oasis.service;

import com.oasis.data.entity.WorkPositionEntity;

public interface WorkPositionService {
    
    WorkPositionEntity getWorkPosition(long workPositionId);
    void createWorkPosition(WorkPositionEntity workPositionEntity);
    
}
