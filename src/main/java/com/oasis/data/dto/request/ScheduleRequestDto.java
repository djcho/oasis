package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {
    private String name;
    private String content;
    private LocalDate date;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
