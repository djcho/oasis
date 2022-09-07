package com.oasis.service.impl;

import com.oasis.data.dto.request.WorklogCreateRequest;
import com.oasis.data.dto.request.WorklogModifyRequest;
import com.oasis.data.entity.Worklog;
import com.oasis.repository.WorklogRepository;
import com.oasis.service.WorklogService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class WorklogServiceImpl implements WorklogService {
    private final WorklogRepository worklogRepository;

    @Override
    public void addWorklog(WorklogCreateRequest worklogCreateRequest) {
        Worklog worklog = Worklog.builder()
                .userSid(worklogCreateRequest.getUserSid())
                .createDttm(LocalDateTime.now())
                .content(worklogCreateRequest.getContent())
                .workingDate(worklogCreateRequest.getWorkingDate())
                .build();

        worklogRepository.save(worklog);
    }

    @Override
    public void modifyWorklog(Long sid, WorklogModifyRequest worklogModifyRequest) {
        Optional<Worklog> optional = worklogRepository.findById(sid);

    }

    @Override
    public void removeWorklog(Long sid) {

    }
}
