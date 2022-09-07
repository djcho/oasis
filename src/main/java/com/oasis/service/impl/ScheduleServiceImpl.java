package com.oasis.service.impl;

import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;
import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.User;
import com.oasis.repository.ScheduleRepository;
import com.oasis.repository.UserRepository;
import com.oasis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    final ScheduleRepository scheduleRepository;
    final UserRepository userRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponseDto getSchedule(Long scheduleSid) throws Exception{
        return this.scheduleRepository.findById(scheduleSid).map(schedule -> ScheduleResponseDto.builder()
                .sid(schedule.getSid())
                .name(schedule.getName())
                .content(schedule.getContent())
                .date(schedule.getDate())
                .createdTime(schedule.getCreatedTime())
                .updatedTime(schedule.getUpdatedTime())
        .build()).orElseThrow(() -> new Exception("not found entity."));
    }

    @Override
    public ScheduleResponseDto saveSchedule(Long userSid, ScheduleRequestDto scheduleRequestDto) {
        User user = this.userRepository.findById(userSid).orElseThrow();

        Schedule schedule = Schedule.builder()
                .name(scheduleRequestDto.getName())
                .content(scheduleRequestDto.getContent())
                .date(scheduleRequestDto.getDate())
                .user(user)
                .build();

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponseDto.builder()
                .sid(savedSchedule.getSid())
                .name(savedSchedule.getName())
                .content(savedSchedule.getContent())
                .date(savedSchedule.getDate())
                .createdTime(savedSchedule.getCreatedTime())
                .updatedTime(savedSchedule.getUpdatedTime())
                .build();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleSid, ScheduleRequestDto scheduleRequestDto) throws Exception {
        Schedule schedule = this.scheduleRepository.findById(scheduleSid).orElseThrow(() -> new Exception("not found entity."));

        if(!scheduleRequestDto.getContent().isEmpty())
            schedule.setContent(scheduleRequestDto.getContent());
        if(!scheduleRequestDto.getName().isEmpty())
            schedule.setName(scheduleRequestDto.getName());
        if(!scheduleRequestDto.getDate().isEqual(scheduleRequestDto.getDate()))
            schedule.setDate(scheduleRequestDto.getDate());

        schedule.setUpdatedTime(LocalDateTime.now());

        Schedule savedSchedule = this.scheduleRepository.save(schedule);
        return ScheduleResponseDto.builder()
                .sid(savedSchedule.getSid())
                .name(savedSchedule.getName())
                .content(savedSchedule.getContent())
                .date(savedSchedule.getDate())
                .createdTime(savedSchedule.getCreatedTime())
                .updatedTime(savedSchedule.getUpdatedTime())
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
                .createdTime(schedule.getCreatedTime())
                .updatedTime(schedule.getUpdatedTime())
                .build()).collect(Collectors.toList());
    }
}
