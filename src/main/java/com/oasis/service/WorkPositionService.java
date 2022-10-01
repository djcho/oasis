package com.oasis.service;

import com.oasis.data.entity.WorkPosition;

public interface WorkPositionService {
    
    WorkPosition getPosition(long sid);
    void createWorkPosition(WorkPosition workPosition);
    
}
