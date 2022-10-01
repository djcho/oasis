package com.oasis.service.impl;

import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.ScheduleType;
import com.oasis.repository.ScheduleRepository;
import com.oasis.repository.MemberRepository;
import com.oasis.repository.ScheduleTypeRepository;
import com.oasis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public ScheduleResponse getSchedule(Long scheduleSid){
        return this.scheduleRepository.findById(scheduleSid).map(schedule -> ScheduleResponse.builder()
                .sid(schedule.getSid())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .createdTime(schedule.getCreatedAt())
                .updatedTime(schedule.getUpdatedAt())
        .build()).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));
    }

    @Override
    public List<ScheduleResponse> getAllSchedules() {
        return this.scheduleRepository.findAll().stream()
                .map(schedule -> ScheduleResponse.builder()
                        .sid(schedule.getSid())
                        .name(schedule.getName())
                        .content(schedule.getContent())
                        .date(schedule.getDate())
                        .createdTime(schedule.getCreatedAt())
                        .updatedTime(schedule.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        Member member = this.memberRepository.findById(scheduleRequest.getMemberSid()).orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));

        Schedule schedule = scheduleRequest.toSchedule();
        schedule.setMember(member);

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponse.builder()
                .sid(savedSchedule.getSid())
                .name(savedSchedule.getName())
                .content(savedSchedule.getContent())
                .date(savedSchedule.getDate())
                .createdTime(savedSchedule.getCreatedAt())
                .updatedTime(savedSchedule.getUpdatedAt())
                .build();
    }

    @Override
    public ScheduleResponse updateSchedule(Long scheduleSid, ScheduleRequest scheduleRequest) {
        Schedule schedule = this.scheduleRepository.findById(scheduleSid).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));

        if(!scheduleRequest.getContent().isEmpty())
            schedule.setContent(scheduleRequest.getContent());
        if(!scheduleRequest.getName().isEmpty())
            schedule.setName(scheduleRequest.getName());
        if(!scheduleRequest.getDate().isEqual(scheduleRequest.getDate()))
            schedule.setDate(scheduleRequest.getDate());
        if(scheduleRequest.getScheduleTypeSid() != 0L) {
            ScheduleType scheduleType = scheduleTypeRepository.findById(scheduleRequest.getScheduleTypeSid())
                    .orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE_TYPE));
            schedule.setScheduleType(scheduleType);
        }

        schedule.setUpdatedAt(LocalDateTime.now());

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponse.builder()
                .sid(savedSchedule.getSid())
                .name(savedSchedule.getName())
                .content(savedSchedule.getContent())
                .date(savedSchedule.getDate())
                .createdTime(savedSchedule.getCreatedAt())
                .updatedTime(savedSchedule.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteSchedule(Long scheduleSid) {
        Schedule schedule = this.scheduleRepository.findById(scheduleSid).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));
        this.scheduleRepository.deleteById(schedule.getSid());
    }

    @Override
    public List<ScheduleResponse> getSchedulesByMember(Long memberSid) {
        Member member = this.memberRepository.findById(memberSid).orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));
        return this.scheduleRepository.findAllByMemberSid(member.getSid()).stream().map(schedule -> ScheduleResponse.builder()
                .sid(schedule.getSid())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .createdTime(schedule.getCreatedAt())
                .updatedTime(schedule.getUpdatedAt())
                .build()).collect(Collectors.toList());
    }
}
