package com.oasis.service;

import com.oasis.data.dto.request.HolidayRequest;
import com.oasis.data.dto.response.HolidayResponse;
import com.oasis.data.entity.Holiday;

import java.util.List;

public interface HolidayService {

    List<HolidayResponse> getHolidayList();
    HolidayResponse saveHoliday(HolidayRequest holidayRequest);

    HolidayResponse updateHoliday(Long sid, HolidayRequest holidayRequest);

    void deleteHoliday(Long sid);
}
