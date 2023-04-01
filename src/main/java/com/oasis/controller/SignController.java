package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.ResponseDtoTemplate;
import com.oasis.data.dto.request.SignInRequestDto;
import com.oasis.data.dto.response.SignInResponseDto;
import com.oasis.mapper.SignMapper;
import com.oasis.service.SignService;
import com.oasis.service.data.SignInResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sign")
public class SignController {
    private final SignService signService;
    private final SignMapper signMapper;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "가입된 사용자를 로그인 시킵니다.")
    public ResponseEntity<ResponseDtoTemplate<SignInResponseDto>> login(
            @RequestBody @Parameter(description = "회원 로그인 요청 정보", required = true) SignInRequestDto signInRequestDto){
        SignInResult signInResult = signService.signIn(signInRequestDto);
        SignInResponseDto signInResponseDto = signMapper.toSignInResponseDto(signInResult);

        return ResponseDtoTemplate.toResponseEntity(ErrorCode.SUCCESS, signInResponseDto);
    }
}
