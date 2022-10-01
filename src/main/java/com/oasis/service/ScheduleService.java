package com.oasis.service;

import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse getSchedule(Long scheduleSid);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse updateSchedule(Long scheduleSid, ScheduleRequest scheduleRequest);
    void deleteSchedule(Long scheduleSid);
    List<ScheduleResponse> getSchedulesByMember(Long memberSid);
}
