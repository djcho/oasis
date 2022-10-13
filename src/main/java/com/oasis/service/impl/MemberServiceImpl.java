package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.entity.Member;
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
        return MemberDto.of(memberRepository.save(memberCreationRequest.toMember()));
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
    public MemberDto getOneMember(Long sid) {
        return memberRepository.findById(sid).map(MemberDto::of).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Override
    public void deleteOneMember(Long sid) {
        memberRepository.deleteById(sid);
    }

    @Override
    public void changePassword(MemberChangePasswordRequest memberChangePasswordRequest) {
        Member member = memberRepository.findById(memberChangePasswordRequest.getSid()).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
        if(passwordEncoder.matches(memberChangePasswordRequest.getOldPassword(), member.getPassword())) {
            member.setPassword(passwordEncoder.encode(memberChangePasswordRequest.getNewPassword()));
            memberRepository.save(member);
        } else {
            throw new CommonException(ErrorCode.INCORRECT_PASSWORD);
        }
    }

}
