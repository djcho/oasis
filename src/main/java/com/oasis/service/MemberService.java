package com.oasis.service;

import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    MemberDto createMember(MemberCreationRequest memberCreationRequest);
    List<MemberDto> getAllMembers(Pageable pageable);
    List<MemberDto> getAllMembers();
    MemberDto getOneMember(Long sid);
    void deleteOneMember(Long sid);
    void changePassword(MemberChangePasswordRequest memberChangePasswordRequest);
    
}
