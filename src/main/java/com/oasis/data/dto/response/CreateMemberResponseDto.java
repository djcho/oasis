package com.oasis.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberResponseDto {
    private String userId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
