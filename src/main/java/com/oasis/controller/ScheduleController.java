package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.service.ScheduleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "검색하려는 결과 페이지 (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "페이지당 레코드 수", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "정렬 포맷: {요소이름}(,asc|desc)" +
                            "기본 정렬 순서는 오름차순, " +
                            "여러 요소 정렬 가능")
    })
    public ResponseEntity<CommonResponse> getAllSchedules(@ApiIgnore(
            "swagger UI가 잘못된 매개변수를 무시하고 ApiImplicitParams 으로 대체"
    )Pageable pageable) {
        Page<ScheduleResponse> scheduleResponseList = this.scheduleService.getAllSchedules(pageable);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }

    /**
     * 특정 사용자의 전체 스케줄 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/member/{memberSid}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "검색하려는 결과 페이지 (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "페이지당 레코드 수", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "정렬 포맷: {요소이름}(,asc|desc)" +
                            "기본 정렬 순서는 오름차순, " +
                            "여러 요소 정렬 가능")
    })
    public ResponseEntity<CommonResponse> getSchedulesByUser(@PathVariable Long memberSid, @ApiIgnore(
            "swagger UI가 잘못된 매개변수를 무시하고 ApiImplicitParams 으로 대체"
    )Pageable pageable){
        Page<ScheduleResponse> scheduleResponseList = this.scheduleService.getSchedulesByMember(memberSid, pageable);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, scheduleResponseList);
    }
}
