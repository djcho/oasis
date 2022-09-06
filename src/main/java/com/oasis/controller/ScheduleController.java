package com.oasis.controller;

import com.oasis.data.dto.request.ScheduleRequestDto;
import com.oasis.data.dto.response.ScheduleResponseDto;
import com.oasis.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleSid){
        try
        {
            this.scheduleService.getSchedule(scheduleSid);
        } catch (Exception ex){
            LOGGER.error("스케쥴 조회 실패, " + ex.getMessage());
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ScheduleResponseDto());
    }

    @PostMapping("/{userSid}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@PathVariable Long userSid, @RequestBody ScheduleRequestDto scheduleRequestDto){
        this.scheduleService.saveSchedule(userSid, scheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ScheduleResponseDto());
    }

    @PatchMapping("/{scheduleSid}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleSid, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        try
        {
            this.scheduleService.updateSchedule(scheduleSid, scheduleRequestDto);
        } catch (Exception ex){
            LOGGER.error("스케쥴 갱신 실패, " + ex.getMessage());
            throw new RuntimeException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ScheduleResponseDto());
    }

    @DeleteMapping("/{scheduleSid}")
    public ResponseEntity<CommonResponseDto> updateSchedule(@PathVariable Long scheduleSid) {
        this.scheduleService.deleteSchedule(scheduleSid);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule() {
        this.scheduleService.get
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<ScheduleResponseDto>());
    }
}
