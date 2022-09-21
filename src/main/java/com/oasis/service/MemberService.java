package com.oasis.service;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    
    Member createMember(UserRequestDto userRequestDto);
    List<Member> getAllMembers(Pageable pageable);
    Member getOneMember(Long sid);
    void deleteOneMember(Long sid);
    
}
