package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.HolidayRequest;
import com.oasis.data.dto.response.HolidayResponse;
import com.oasis.data.entity.HolidayEntity;
import com.oasis.exception.CommonException;
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
                .map(d -> new HolidayResponse(d.getId(), d.getDate(), d.getDescription()))
                .collect(Collectors.toList());
    }

    @Transactional
    public HolidayResponse saveHoliday(HolidayRequest request){
        HolidayEntity holidayEntity = HolidayEntity.builder()
            .day(request.getDay())
            .year(request.getYear())
            .month(request.getMonth())
            .date(request.getDate())
            .description(request.getDescription())
            .build();

        HolidayEntity savedHolidayEntity = holidayRepository.save(holidayEntity);

        return HolidayResponse.builder()
                .sid(savedHolidayEntity.getId())
                .date(savedHolidayEntity.getDate())
                .description(savedHolidayEntity.getDescription())
                .build();
    }

    @Transactional
    public HolidayResponse updateHoliday(Long holidayId, HolidayRequest request){
        HolidayEntity holidayEntity = holidayRepository.findById(holidayId)
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_HOLIDAY));

        if(holidayEntity.getDate().isEqual(request.getDate()) == false){
            holidayEntity.setDate(request.getDate());
        }
        if(holidayEntity.getYear().isEmpty() == false){
            holidayEntity.setYear(request.getYear());
        }
        if(holidayEntity.getMonth().isEmpty() == false) {
            holidayEntity.setMonth(request.getMonth());
        }
        if(holidayEntity.getDay().isEmpty() == false){
            holidayEntity.setDay(request.getDay());
        }
        if(holidayEntity.getDescription().isEmpty() == false){
            holidayEntity.setDescription(request.getDescription());
        }

        HolidayEntity savedHolidayEntity = holidayRepository.save(holidayEntity);

        return HolidayResponse.builder()
                .sid(savedHolidayEntity.getId())
                .date(savedHolidayEntity.getDate())
                .description(savedHolidayEntity.getDescription())
                .build();
    }

    @Transactional
    public void deleteHoliday(Long holidayId){
        HolidayEntity holidayEntity = holidayRepository.findById(holidayId)
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_HOLIDAY));

        holidayRepository.deleteById(holidayId);
    }
}
