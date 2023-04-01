package com.oasis.service.impl;

import com.oasis.data.dto.request.WorkLogCreateRequest;
import com.oasis.data.dto.request.WorkLogModifyRequest;
import com.oasis.data.entity.MemberEntity;
import com.oasis.data.entity.WorkLogEntity;
import com.oasis.exception.CommonException;
import com.oasis.repository.MemberRepository;
import com.oasis.repository.WorkLogRepository;
import com.oasis.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.oasis.common.constant.ErrorCode.NOT_FOUND_MEMBER;

@RequiredArgsConstructor
@Service
public class WorkLogServiceImpl implements WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final MemberRepository memberRepository;

    @Override
    public void addWorkLog(WorkLogCreateRequest workLogCreateRequest) {
        MemberEntity memberEntity = memberRepository.findById(workLogCreateRequest.getMemberId())
                .orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));
        WorkLogEntity workLogEntity = WorkLogEntity.builder()
                .member(memberEntity)
                .content(workLogCreateRequest.getContent())
                .workingDate(workLogCreateRequest.getWorkingDate())
                .build();

        workLogRepository.save(workLogEntity);
    }

    @Override
    public void modifyWorkLog(Long workLogId, WorkLogModifyRequest workLogModifyRequest) {
        Optional<WorkLogEntity> optional = workLogRepository.findById(workLogId);

    }

    @Override
    public void removeWorkLog(Long workLogId) {

    }
}
