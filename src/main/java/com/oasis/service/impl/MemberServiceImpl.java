package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.CreateMemberRequestDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.entity.MemberEntity;
import com.oasis.data.entity.RoleEntity;
import com.oasis.exception.CommonException;
import com.oasis.mapper.MemberMapper;
import com.oasis.repository.InvitationRepository;
import com.oasis.repository.MemberRepository;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final InvitationRepository invitationRepository;
    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public MemberDto createMember(String signUpToken, CreateMemberRequestDto createMemberRequestDto) {
        if(memberRepository.existsMemberByUid(createMemberRequestDto.getUid())) {
            throw new CommonException(ErrorCode.DUPLICATION_MEMBER);
        }
        createMemberRequestDto.setPassword(passwordEncoder.encode(createMemberRequestDto.getPassword()));
        MemberEntity member = memberMapper.createMemberRequestDtoToEntity(createMemberRequestDto);
        member.getRoles().add(RoleEntity.builder()
                .name("USER")
                .build());

        MemberEntity savedMember = memberRepository.save(member);

        invitationRepository.deleteByToken(signUpToken);
        return memberMapper.memberEntityToMemberDto(savedMember);
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
    public MemberDto getMember(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDto::of).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public MemberDto changePassword(MemberChangePasswordRequest memberChangePasswordRequest) {
        MemberEntity memberEntity = memberRepository.findById(memberChangePasswordRequest.getSid()).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
        if(passwordEncoder.matches(memberChangePasswordRequest.getOldPassword(), memberEntity.getPassword())) {
            memberEntity.setPassword(passwordEncoder.encode(memberChangePasswordRequest.getNewPassword()));
            memberRepository.save(memberEntity);
        } else {
            throw new CommonException(ErrorCode.INCORRECT_PASSWORD);
        }
        return MemberDto.of(memberEntity);
    }

}
