package com.oasis.service.impl;

import com.oasis.data.entity.WorkPositionEntity;
import com.oasis.repository.WorkPositionRepository;
import com.oasis.service.WorkPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkPositionServiceImpl implements WorkPositionService {
    
    private final WorkPositionRepository workPositionRepository;

    @Override
    public WorkPositionEntity getWorkPosition(long workPositionId) {
        return workPositionRepository.getReferenceById(workPositionId);
    }

    @Override
    public void createWorkPosition(WorkPositionEntity workPositionEntity) {
        workPositionRepository.save(workPositionEntity);
    }
}
