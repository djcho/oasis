package com.oasis.service.impl;

import com.oasis.data.entity.WorkPosition;
import com.oasis.repository.WorkPositionRepository;
import com.oasis.service.WorkPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkPositionServiceImpl implements WorkPositionService {
    
    private final WorkPositionRepository workPositionRepository;

    @Override
    public WorkPosition getPosition(long sid) {
        return workPositionRepository.getReferenceById(sid);
    }

    @Override
    public void createWorkPosition(WorkPosition workPosition) {
        workPositionRepository.save(workPosition);
    }
}
