package com.oasis.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {
    private Long sid;
    private Long parentSid;
    private String name;
    private int level;
}
