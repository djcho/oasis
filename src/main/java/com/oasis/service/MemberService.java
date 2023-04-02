package com.oasis.service;

import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.CreateMemberRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    MemberDto createMember(String signUpToken, CreateMemberRequestDto createMemberRequestDto);
    List<MemberDto> getAllMembers(Pageable pageable);
    List<MemberDto> getAllMembers();
    MemberDto getMember(Long memberId);
    void deleteMember(Long memberId);
    MemberDto changePassword(MemberChangePasswordRequest memberChangePasswordRequest);
    
}
