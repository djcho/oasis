package com.oasis.service;

import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    MemberDto createMember(MemberCreationRequest memberCreationRequest);
    List<MemberDto> getAllMembers(Pageable pageable);
    List<MemberDto> getAllMembers();
    MemberDto getOneMember(Long id);
    void deleteOneMember(Long id);
    void changePassword(MemberChangePasswordRequest memberChangePasswordRequest);
    
}
