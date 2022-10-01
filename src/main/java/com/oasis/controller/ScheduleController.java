package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 특정 스케줄 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> getSchedule(@PathVariable Long scheduleSid) {
        ScheduleResponse scheduleResponse = this.scheduleService.getSchedule(scheduleSid);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponse);
    }

    /**
     * 스케줄 생성
     * */
    @PostMapping
    public ResponseEntity<CommonResponse> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest){
        ScheduleResponse scheduleResponse = this.scheduleService.saveSchedule(scheduleRequest);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponse);
    }

    /**
     * 스케줄 수정
     * */
    @PatchMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> updateSchedule(@PathVariable Long scheduleSid, @RequestBody ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = this.scheduleService.updateSchedule(scheduleSid, scheduleRequest);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponse);
    }

    /**
     * 스케줄 제거
     * */
    @DeleteMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponse> updateSchedule(@PathVariable Long scheduleSid) {
        this.scheduleService.deleteSchedule(scheduleSid);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    /**
     * 전체 스케줄 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping
    public ResponseEntity<CommonResponse> getAllSchedules() {
        List<ScheduleResponse> scheduleResponseList = this.scheduleService.getAllSchedules();

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }

    /**
     * 특정 사용자의 전체 스케줄 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/member/{memberSid}")
    public ResponseEntity<CommonResponse> getSchedulesByUser(@PathVariable Long memberSid){
        List<ScheduleResponse> scheduleResponseList = this.scheduleService.getSchedulesByMember(memberSid);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }
}
