package com.oasis.service.impl;

import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.data.entity.*;
import com.oasis.exception.CommonException;
import com.oasis.repository.MemberRepository;
import com.oasis.repository.ScheduleRepository;
import com.oasis.repository.ScheduleTypeRepository;
import com.oasis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.oasis.common.constant.ErrorCode.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    final ScheduleRepository scheduleRepository;
    final ScheduleTypeRepository scheduleTypeRepository;
    final MemberRepository memberRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleTypeRepository scheduleTypeRepository, MemberRepository memberRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleTypeRepository = scheduleTypeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ScheduleResponse getSchedule(Long scheduleId){
        return this.scheduleRepository.findById(scheduleId).map(schedule -> ScheduleResponse.builder()
                .sid(schedule.getId())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .member(ScheduleResponse.Member.builder()
                        .id(schedule.getMember().getId())
                        .uid(schedule.getMember().getUid())
                        .name(schedule.getMember().getName())
                        //.role(schedule.getMember().getMemberRole().value())
                        .workDuty(schedule.getMember().getWorkDuty().getName())
                        .workPosition(schedule.getMember().getWorkPosition().getName())
                        .department(schedule.getMember().getDepartment().getName())
                        .build())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
        .build()).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));
    }

    @Override
    public Page<ScheduleResponse> getAllSchedules(Pageable pageable) {
        return new PageImpl<>(this.scheduleRepository.findAll(pageable).stream()
                .map(schedule -> ScheduleResponse.builder()
                        .sid(schedule.getId())
                        .name(schedule.getName())
                        .content(schedule.getContent())
                        .date(schedule.getDate())
                        .member(ScheduleResponse.Member.builder()
                                .id(schedule.getMember().getId())
                                .uid(schedule.getMember().getUid())
                                .name(schedule.getMember().getName())
                               // .role(schedule.getMember().getMemberRole().value())
                                .workDuty(schedule.getMember().getWorkDuty().getName())
                                .workPosition(schedule.getMember().getWorkPosition().getName())
                                .department(schedule.getMember().getDepartment().getName())
                                .build())
                        .createdAt(schedule.getCreatedAt())
                        .updatedAt(schedule.getUpdatedAt())
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        MemberEntity memberEntity = this.memberRepository.findById(scheduleRequest.getMemberId()).orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));
        ScheduleTypeEntity scheduleTypeEntity = scheduleTypeRepository.findById(scheduleRequest.getScheduleTypeSid()).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE_TYPE));

        ScheduleEntity scheduleEntity = scheduleRequest.toSchedule(memberEntity, scheduleTypeEntity);
        ScheduleEntity savedScheduleEntity = this.scheduleRepository.save(scheduleEntity);
        return ScheduleResponse.builder()
                .sid(savedScheduleEntity.getId())
                .name(savedScheduleEntity.getName())
                .content(savedScheduleEntity.getContent())
                .date(savedScheduleEntity.getDate())
                .member(ScheduleResponse.Member.builder()
                        .id(scheduleEntity.getMember().getId())
                        .uid(scheduleEntity.getMember().getUid())
                        .name(scheduleEntity.getMember().getName())
                     //   .role(scheduleEntity.getMember().getMemberRole().value())
                        .workDuty(scheduleEntity.getMember().getWorkDuty().getName())
                        .workPosition(scheduleEntity.getMember().getWorkPosition().getName())
                        .department(scheduleEntity.getMember().getDepartment().getName())
                        .build())
                .createdAt(scheduleEntity.getCreatedAt())
                .updatedAt(scheduleEntity.getUpdatedAt())
                .build();
    }

    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest) {
        ScheduleEntity scheduleEntity = this.scheduleRepository.findById(scheduleId).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));

        if(!scheduleRequest.getContent().isEmpty())
            scheduleEntity.setContent(scheduleRequest.getContent());
        if(!scheduleRequest.getName().isEmpty())
            scheduleEntity.setName(scheduleRequest.getName());
        if(!scheduleRequest.getDate().isEqual(scheduleRequest.getDate()))
            scheduleEntity.setDate(scheduleRequest.getDate());
        if(scheduleRequest.getScheduleTypeSid() != 0L) {
            ScheduleTypeEntity scheduleTypeEntity = scheduleTypeRepository.findById(scheduleRequest.getScheduleTypeSid())
                    .orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE_TYPE));
            scheduleEntity.setScheduleType(scheduleTypeEntity);
        }

        scheduleEntity.setUpdatedAt(LocalDateTime.now());

        ScheduleEntity savedScheduleEntity = this.scheduleRepository.save(scheduleEntity);
        return ScheduleResponse.builder()
                .sid(savedScheduleEntity.getId())
                .name(savedScheduleEntity.getName())
                .content(savedScheduleEntity.getContent())
                .date(savedScheduleEntity.getDate())
                .createdAt(savedScheduleEntity.getCreatedAt())
                .updatedAt(savedScheduleEntity.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        ScheduleEntity scheduleEntity = this.scheduleRepository.findById(scheduleId).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));
        this.scheduleRepository.deleteById(scheduleEntity.getId());
    }

    @Override
    public Page<ScheduleResponse> getSchedulesByMember(Long memberId, Pageable pageable) {
        MemberEntity memberEntity = this.memberRepository.findById(memberId).orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));
        return new PageImpl<>(this.scheduleRepository.findAllByMemberId(memberEntity.getId(), pageable).stream().map(schedule -> ScheduleResponse.builder()
                .sid(schedule.getId())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .member(ScheduleResponse.Member.builder()
                        .id(schedule.getMember().getId())
                        .uid(schedule.getMember().getUid())
                        .name(schedule.getMember().getName())
               //         .role(schedule.getMember().getMemberRole().value())
                        .workDuty(schedule.getMember().getWorkDuty().getName())
                        .workPosition(schedule.getMember().getWorkPosition().getName())
                        .department(schedule.getMember().getDepartment().getName())
                        .build())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build()).collect(Collectors.toList()));
    }
}
