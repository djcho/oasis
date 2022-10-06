package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/monthly")
    private ResponseEntity<CommonResponse> monthly(@RequestParam int year) {




        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, statisticsService.monthly(year));
    }

    @GetMapping("/users")
    private ResponseEntity<CommonResponse> users(@RequestParam String startDate,
                                                 @RequestParam String endDate) {


        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @GetMapping("/users/{userSid}")
    private ResponseEntity<CommonResponse> usersDetail(@RequestParam String startDate,
                                                       @RequestParam String endDate,
                                                       @PathVariable Long userSid) {


        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }



}
