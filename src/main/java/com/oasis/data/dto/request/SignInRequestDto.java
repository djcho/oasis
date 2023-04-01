package com.oasis.data.dto.request;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SignInRequestDto {
    private String userId;
    private String password;
}
