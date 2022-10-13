package com.oasis.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDetailResponse {
    private Long sid;
    private Long parentSid;
    private String name;
    private int level;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
