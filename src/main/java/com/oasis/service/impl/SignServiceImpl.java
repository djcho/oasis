package com.oasis.service.impl;

import com.oasis.data.dto.request.SignInRequestDto;
import com.oasis.data.entity.MemberEntity;
import com.oasis.exception.CommonException;
import com.oasis.repository.MemberRepository;
import com.oasis.security.ApiAccessTokenProvider;
import com.oasis.service.SignService;
import com.oasis.service.data.SignInResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.oasis.common.constant.ErrorCode.INCORRECT_PASSWORD;
import static com.oasis.common.constant.ErrorCode.NOT_FOUND_MEMBER;


@Service
public class SignServiceImpl implements SignService {
    private final MemberRepository memberRepository;
    private final ApiAccessTokenProvider apiAccessTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public SignServiceImpl(MemberRepository memberRepository, ApiAccessTokenProvider apiAccessTokenProvider, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.apiAccessTokenProvider = apiAccessTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignInResult signIn(SignInRequestDto signInRequestDto) throws RuntimeException {
        //1. 유효성 확인 - 가입된 사용자 여부 확인
        MemberEntity memberEntity = memberRepository.findByUid(signInRequestDto.getUserId())
                .orElseThrow(() -> new CommonException(NOT_FOUND_MEMBER));

        //2. 유효성 확인 - 비밀번호 일치 여부 확인
        if(!passwordEncoder.matches(signInRequestDto.getPassword(), memberEntity.getPassword()))
            throw new CommonException(INCORRECT_PASSWORD);

        //3. API 접근 토큰 발급
        String apiAccessToken = apiAccessTokenProvider.generateToken(String.valueOf(memberEntity.getUid()));
        return SignInResult.builder()
                .apiAccessToken(apiAccessToken)
                .build();
    }
}
