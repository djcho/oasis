package com.oasis.service.impl;

import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;
import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.Member;
import com.oasis.repository.ScheduleRepository;
import com.oasis.repository.MemberRepository;
import com.oasis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.oasis.common.constant.ErrorCode.NOT_FOUND_SCHEDULE;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    final ScheduleRepository scheduleRepository;
    final MemberRepository memberRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, MemberRepository memberRepository) {
        this.scheduleRepository = scheduleRepository;
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
        Member member = this.memberRepository.findById(userSid).orElseThrow(() -> new CommonException(NOT_FOUND_SCHEDULE));

        Schedule schedule = Schedule.builder()
                .name(scheduleRequestDto.getName())
                .content(scheduleRequestDto.getContent())
                .date(scheduleRequestDto.getDate())
                .member(member)
                .build();

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
        this.scheduleRepository.deleteById(scheduleSid);
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
