package com.oasis.service;

import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto getSchedule(Long scheduleSid);
    List<ScheduleResponseDto> getAllSchedules();
    ScheduleResponseDto saveSchedule(Long userSid, ScheduleRequestDto scheduleRequestDto);
    ScheduleResponseDto updateSchedule(Long scheduleSid, ScheduleRequestDto scheduleRequestDto);
    void deleteSchedule(Long scheduleSid);
    List<ScheduleResponseDto> getScheduleByUserSid(Long userSid);
}
