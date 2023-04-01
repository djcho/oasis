package com.oasis.data.dto.request;

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
