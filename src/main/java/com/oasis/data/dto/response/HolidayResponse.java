package com.oasis.data.dto.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HolidayResponse {
    private Long sid;
    private LocalDate date;
    private String description;
}
