package com.oasis.service;


import com.oasis.data.dto.request.SignInRequestDto;
import com.oasis.data.dto.request.SignUpRequestDto;
import com.oasis.service.data.SignInResult;
import com.oasis.service.data.SignUpResult;

public interface SignService {
    SignInResult signIn(SignInRequestDto signInRequestDto) throws RuntimeException;
}
