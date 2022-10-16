package com.oasis.service;

import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.ScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse getSchedule(Long scheduleSid);
    Page<ScheduleResponse> getAllSchedules(Pageable pageable);
    ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse updateSchedule(Long scheduleSid, ScheduleRequest scheduleRequest);
    void deleteSchedule(Long scheduleSid);
    Page<ScheduleResponse> getSchedulesByMember(Long memberSid, Pageable pageable);
}
