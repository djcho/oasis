package com.oasis.controller.rest;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.HolidayRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.HolidayResponse;
import com.oasis.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping
    public ResponseEntity<CommonResponse> getAllHoliday(){
        List<HolidayResponse> holidayResponsesList = holidayService.getHolidayList();
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, holidayResponsesList);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createHoliday(@RequestBody HolidayRequest holidayRequest){
        HolidayResponse holidayResponse = holidayService.saveHoliday(holidayRequest);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, holidayResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> updateHoliday(@PathVariable Long id, @RequestBody HolidayRequest request){
        HolidayResponse holidayResponse = holidayService.updateHoliday(id, request);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, holidayResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteHoliday(@PathVariable Long id){
        holidayService.deleteHoliday(id);

        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }
}
