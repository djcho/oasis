package com.oasis.service.impl;

import com.oasis.data.entity.WorkDuty;
import com.oasis.repository.WorkDutyRepository;
import com.oasis.service.WorkDutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkDutyServiceImpl implements WorkDutyService {
    
    private final WorkDutyRepository workDutyRepository;
    
    @Override
    public void createWorkDuty(WorkDuty workDuty) {
        workDutyRepository.save(workDuty);
    }

    @Override
    public WorkDuty getWorkDuty(long sid) {
        return workDutyRepository.getReferenceById(sid);
    }

}
