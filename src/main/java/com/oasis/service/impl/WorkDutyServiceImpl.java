package com.oasis.service.impl;

import com.oasis.data.entity.WorkDutyEntity;
import com.oasis.repository.WorkDutyRepository;
import com.oasis.service.WorkDutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkDutyServiceImpl implements WorkDutyService {
    
    private final WorkDutyRepository workDutyRepository;
    
    @Override
    public void createWorkDuty(WorkDutyEntity workDutyEntity) {
        workDutyRepository.save(workDutyEntity);
    }

    @Override
    public WorkDutyEntity getWorkDuty(long sid) {
        return workDutyRepository.getReferenceById(sid);
    }

}
