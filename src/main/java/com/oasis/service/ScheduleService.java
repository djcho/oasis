package com.oasis.service;

import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto getSchedule(Long scheduleSid) throws Exception;
    ScheduleResponseDto saveSchedule(Long userSid, ScheduleRequestDto scheduleRequestDto);
    ScheduleResponseDto updateSchedule(Long scheduleSid, ScheduleRequestDto scheduleRequestDto) throws  Exception;
    void deleteSchedule(Long scheduleSid);
    List<ScheduleResponseDto> getScheduleByUserSid(Long userSid);
}
