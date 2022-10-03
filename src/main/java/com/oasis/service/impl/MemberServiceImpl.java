package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.common.exception.CommonException;
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

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member createMember(MemberCreationRequest memberCreationRequest) {
        if(memberRepository.findById(memberCreationRequest.getId()).isPresent()) {
            throw new CommonException(ErrorCode.DUPLICATION_MEMBER);
        }
        memberCreationRequest.setPassword(passwordEncoder.encode(memberCreationRequest.getPassword()));
        return memberRepository.save(memberCreationRequest.toMember());
    }

    @Override
    public List<Member> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).toList();
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getOneMember(Long sid) {
        return memberRepository.findById(sid).orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Override
    public void deleteOneMember(Long sid) {
        memberRepository.deleteById(sid);
    }

    @Override
    public void changePassword(MemberChangePasswordRequest memberChangePasswordRequest) {
        Member member = this.getOneMember(memberChangePasswordRequest.getSid());
        if(passwordEncoder.matches(memberChangePasswordRequest.getOldPassword(), member.getPassword())) {
            member.setPassword(passwordEncoder.encode(memberChangePasswordRequest.getNewPassword()));
            memberRepository.save(member);
        } else {
            throw new CommonException(ErrorCode.INCORRECT_PASSWORD);
        }
    }

}
