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
    private final WorkLogRepository worklogRepository;

    @Override
    public void addWorkLog(WorkLogCreateRequest worklogCreateRequest) {
        WorkLog worklog = WorkLog.builder()
                .userSid(worklogCreateRequest.getUserSid())
                .content(worklogCreateRequest.getContent())
                .workingDate(worklogCreateRequest.getWorkingDate())
                .build();

        worklogRepository.save(worklog);
    }

    @Override
    public void modifyWorkLog(Long sid, WorkLogModifyRequest worklogModifyRequest) {
        Optional<WorkLog> optional = worklogRepository.findById(sid);

    }

    @Override
    public void removeWorkLog(Long sid) {

    }
}
