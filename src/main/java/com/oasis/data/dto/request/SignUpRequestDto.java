package com.oasis.data.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SignUpRequestDto {
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Email(message = "아이디는 이메일 형식이어야 합니다.")
    private String userId;
    @NotEmpty(message = "이름를 입력해주세요.")
    private String name;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
}
