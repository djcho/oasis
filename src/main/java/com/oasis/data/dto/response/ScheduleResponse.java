package com.oasis.data.dto.response;

import com.oasis.data.entity.WorkPosition;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
    private Long sid;
    private String name;
    private String content;
    private LocalDate date;
    private Member member;

    @Getter
    @Builder
    public static class Member {
        private Long sid;
        private String id;
        private String name;
        private String role;
        private String workDuty;
        private String workPosition;
        private String department;
    }
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
