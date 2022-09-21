package com.oasis.service.impl;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.Member;
import com.oasis.repository.MemberRepository;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;

    @Override
    public Member createMember(UserRequestDto userRequestDto) {
        return memberRepository.save(userRequestDto.toUser());
    }

    @Override
    public List<Member> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).toList();
    }

    @Override
    public Member getOneMember(Long sid) {
        // TODO :: exception 생각 
        return memberRepository.findById(sid).orElseThrow();
    }

    @Override
    public void deleteOneMember(Long sid) {
        memberRepository.deleteById(sid);
    }

}
