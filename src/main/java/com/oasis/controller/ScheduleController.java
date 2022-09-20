package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.ScheduleResponseDto;
import com.oasis.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> getSchedule(@PathVariable Long scheduleSid) {
        ScheduleResponseDto scheduleResponseDto = this.scheduleService.getSchedule(scheduleSid);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseDto);
    }

    @PostMapping("/{userSid}")
    public ResponseEntity<CommonResponse> createSchedule(@PathVariable Long userSid, @RequestBody ScheduleRequestDto scheduleRequestDto){
        ScheduleResponseDto scheduleResponseDto = this.scheduleService.saveSchedule(userSid, scheduleRequestDto);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseDto);
    }

    @PatchMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> updateSchedule(@PathVariable Long scheduleSid, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto scheduleResponseDto = this.scheduleService.updateSchedule(scheduleSid, scheduleRequestDto);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseDto);
    }

    @DeleteMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> updateSchedule(@PathVariable Long scheduleSid) {
        this.scheduleService.deleteSchedule(scheduleSid);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllSchedule() {
        List<ScheduleResponseDto> scheduleResponseDtoList = this.scheduleService.getAllSchedules();

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseDtoList);
    }
}
