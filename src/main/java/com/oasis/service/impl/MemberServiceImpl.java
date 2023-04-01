package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.entity.MemberEntity;
import com.oasis.exception.CommonException;
import com.oasis.repository.MemberRepository;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDto createMember(MemberCreationRequest memberCreationRequest) {
        if(memberRepository.findById(memberCreationRequest.getId()).isPresent()) {
            throw new CommonException(ErrorCode.DUPLICATION_MEMBER);
        }
        memberCreationRequest.setPassword(passwordEncoder.encode(memberCreationRequest.getPassword()));
        return MemberDto.of(memberRepository.save(memberCreationRequest.toMemberEntity()));
    }

    @Override
    public List<MemberDto> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).stream().map(MemberDto::of).collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream().map(MemberDto::of).collect(Collectors.toList());
    }

    @Override
    public MemberDto getOneMember(Long id) {
        return memberRepository.findById(id).map(MemberDto::of).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Override
    public void deleteOneMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void changePassword(MemberChangePasswordRequest memberChangePasswordRequest) {
        MemberEntity memberEntity = memberRepository.findById(memberChangePasswordRequest.getSid()).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
        if(passwordEncoder.matches(memberChangePasswordRequest.getOldPassword(), memberEntity.getPassword())) {
            memberEntity.setPassword(passwordEncoder.encode(memberChangePasswordRequest.getNewPassword()));
            memberRepository.save(memberEntity);
        } else {
            throw new CommonException(ErrorCode.INCORRECT_PASSWORD);
        }
    }

}
