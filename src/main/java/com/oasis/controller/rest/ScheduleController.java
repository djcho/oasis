package com.oasis.controller.rest;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Operation(summary = "전체 스케줄 조회", description = "모든 스케쥴을 검색합니다.")
    public ResponseEntity<CommonResponse> getAllSchedules(Pageable pageable) {
        Page<ScheduleResponse> scheduleResponseList = this.scheduleService.getAllSchedules(pageable);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }

    /**
     * 특정 사용자의 전체 스케줄 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/member/{memberSid}")
    @Operation(summary = "사용자의 전체 스케줄 조회", description = "지정된 ID를 가진 회원의 일정 페이지를 검색합니다.")
    public ResponseEntity<CommonResponse> getSchedulesByUser(@PathVariable Long memberSid, Pageable pageable){
        Page<ScheduleResponse> scheduleResponseList = this.scheduleService.getSchedulesByMember(memberSid, pageable);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }
}
