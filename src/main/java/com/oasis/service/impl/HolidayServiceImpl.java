package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.request.HolidayRequest;
import com.oasis.data.dto.response.HolidayResponse;
import com.oasis.data.entity.Holiday;
import com.oasis.repository.HolidayRepository;
import com.oasis.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    @Transactional
    public List<HolidayResponse> getHolidayList(){
        return holidayRepository.findAll().stream()
                .map(d -> new HolidayResponse(d.getSid(), d.getDate(), d.getDescription()))
                .collect(Collectors.toList());
    }

    @Transactional
    public HolidayResponse saveHoliday(HolidayRequest request){
        Holiday holiday = Holiday.builder()
            .day(request.getDay())
            .year(request.getYear())
            .month(request.getMonth())
            .date(request.getDate())
            .description(request.getDescription())
            .build();

        Holiday savedHoliday = holidayRepository.save(holiday);

        return HolidayResponse.builder()
                .sid(savedHoliday.getSid())
                .date(savedHoliday.getDate())
                .description(savedHoliday.getDescription())
                .build();
    }

    @Transactional
    public HolidayResponse updateHoliday(Long sid, HolidayRequest request){
        Holiday holiday = holidayRepository.findById(sid)
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_HOLIDAY));

        if(holiday.getDate().isEqual(request.getDate()) == false){
            holiday.setDate(request.getDate());
        }
        if(holiday.getYear().isEmpty() == false){
            holiday.setYear(request.getYear());
        }
        if(holiday.getMonth().isEmpty() == false) {
            holiday.setMonth(request.getMonth());
        }
        if(holiday.getDay().isEmpty() == false){
            holiday.setDay(request.getDay());
        }
        if(holiday.getDescription().isEmpty() == false){
            holiday.setDescription(request.getDescription());
        }

        Holiday savedHoliday = holidayRepository.save(holiday);

        return HolidayResponse.builder()
                .sid(savedHoliday.getSid())
                .date(savedHoliday.getDate())
                .description(savedHoliday.getDescription())
                .build();
    }

    @Transactional
    public void deleteHoliday(Long sid){
        Holiday holiday = holidayRepository.findById(sid)
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_HOLIDAY));

        holidayRepository.deleteById(sid);
    }
}
