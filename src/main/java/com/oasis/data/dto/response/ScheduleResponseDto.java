package com.oasis.data.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDto {
    private Long sid;
    private String name;
    private String content;
    private LocalDate date;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
