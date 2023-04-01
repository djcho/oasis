package com.oasis.service.data;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResult {
    private String apiAccessToken;
}
