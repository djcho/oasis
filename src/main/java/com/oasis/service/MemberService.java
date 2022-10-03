package com.oasis.service;

import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    
    Member createMember(MemberCreationRequest memberCreationRequest);
    void createMember(Member member);
    List<Member> getAllMembers(Pageable pageable);
    List<Member> getAllMembers();
    Member getOneMember(Long sid);
    void deleteOneMember(Long sid);
    void changePassword(MemberChangePasswordRequest memberChangePasswordRequest);
    
}
