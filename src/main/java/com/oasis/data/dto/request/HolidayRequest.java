package com.oasis.data.dto.request;

import com.oasis.data.entity.Holiday;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayRequest {
    private LocalDate date;
    private String year;
    private String month;
    private String day;
    private String description;
}
