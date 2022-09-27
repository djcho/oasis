package com.oasis.service.impl;

import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;
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
    public ScheduleResponseDto getSchedule(Long scheduleSid){
        return this.scheduleRepository.findById(scheduleSid).map(schedule -> ScheduleResponseDto.builder()
                .sid(schedule.getSid())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .createdTime(schedule.getCreatedAt())
                .updatedTime(schedule.getUpdatedAt())
        .build()).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));
    }

    @Override
    public List<ScheduleResponseDto> getAllSchedules() {
        return this.scheduleRepository.findAll().stream()
                .map(schedule -> ScheduleResponseDto.builder()
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
    public ScheduleResponseDto saveSchedule(Long userSid, ScheduleRequestDto scheduleRequestDto) {
        Member member = this.memberRepository.findById(userSid).orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));

        Schedule schedule = scheduleRequestDto.toSchedule();
        schedule.setMember(member);

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponseDto.builder()
                .sid(savedSchedule.getSid())
                .name(savedSchedule.getName())
                .content(savedSchedule.getContent())
                .date(savedSchedule.getDate())
                .createdTime(savedSchedule.getCreatedAt())
                .updatedTime(savedSchedule.getUpdatedAt())
                .build();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleSid, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = this.scheduleRepository.findById(scheduleSid).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));

        if(!scheduleRequestDto.getContent().isEmpty())
            schedule.setContent(scheduleRequestDto.getContent());
        if(!scheduleRequestDto.getName().isEmpty())
            schedule.setName(scheduleRequestDto.getName());
        if(!scheduleRequestDto.getDate().isEqual(scheduleRequestDto.getDate()))
            schedule.setDate(scheduleRequestDto.getDate());
        if(scheduleRequestDto.getScheduleTypeSid() != 0L) {
            ScheduleType scheduleType = scheduleTypeRepository.findById(scheduleRequestDto.getScheduleTypeSid())
                    .orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE_TYPE));
            schedule.setScheduleType(scheduleType);
        }

        schedule.setUpdatedAt(LocalDateTime.now());

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponseDto.builder()
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
    public List<ScheduleResponseDto> getScheduleByUserSid(Long userSid) {
        return this.scheduleRepository.findAll().stream().map(schedule -> ScheduleResponseDto.builder()
                .sid(schedule.getSid())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .createdTime(schedule.getCreatedAt())
                .updatedTime(schedule.getUpdatedAt())
                .build()).collect(Collectors.toList());
    }
}
