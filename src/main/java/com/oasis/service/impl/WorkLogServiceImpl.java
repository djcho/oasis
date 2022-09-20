package com.oasis.service.impl;

import com.oasis.data.dto.request.WorkLogCreateRequest;
import com.oasis.data.dto.request.WorkLogModifyRequest;
import com.oasis.data.entity.WorkLog;
import com.oasis.repository.WorkLogRepository;
import com.oasis.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkLogServiceImpl implements WorkLogService {
    private final WorkLogRepository workLogRepository;

    @Override
    public void addWorkLog(WorkLogCreateRequest workLogCreateRequest) {
        WorkLog workLog = WorkLog.builder()
                .userSid(workLogCreateRequest.getUserSid())
                .content(workLogCreateRequest.getContent())
                .workingDate(workLogCreateRequest.getWorkingDate())
                .build();

        workLogRepository.save(workLog);
    }

    @Override
    public void modifyWorkLog(Long sid, WorkLogModifyRequest workLogModifyRequest) {
        Optional<WorkLog> optional = workLogRepository.findById(sid);

    }

    @Override
    public void removeWorkLog(Long sid) {

    }
}
