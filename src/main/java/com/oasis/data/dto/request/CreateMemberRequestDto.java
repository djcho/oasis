package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto {
    private String uid;
    private String name;
    private String password;
}
