package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/monthly")
    private ResponseEntity<CommonResponse> monthly(@RequestParam String startDate, @RequestParam String endDate) {


        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }



}
