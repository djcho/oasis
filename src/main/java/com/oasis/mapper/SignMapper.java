package com.oasis.mapper;

import com.oasis.data.dto.request.SignUpRequestDto;
import com.oasis.data.dto.response.SignInResponseDto;
import com.oasis.data.entity.MemberEntity;
import com.oasis.service.data.SignInResult;
import com.oasis.service.data.SignUpResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignMapper {
    private final ModelMapper modelMapper;

    public MemberEntity toEntity(SignUpRequestDto signUpRequestDto) {
        modelMapper.typeMap(SignUpRequestDto.class, MemberEntity.class)
                .addMappings(mapper -> mapper.map(SignUpRequestDto::getUserId, MemberEntity::setUid));
        return modelMapper.map(signUpRequestDto, MemberEntity.class);
    }

    public SignUpResult toSignUpResult(MemberEntity memberEntity){
        modelMapper.typeMap(MemberEntity.class, SignUpResult.class)
                .addMappings(mapper -> mapper.map(MemberEntity::getUid, SignUpResult::setUserId));
        return modelMapper.map(memberEntity, SignUpResult.class);
    }

    public SignInResponseDto toSignInResponseDto(SignInResult signInResult) {
        return modelMapper.map(signInResult, SignInResponseDto.class);
    }
}
