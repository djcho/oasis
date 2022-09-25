package com.oasis.service.impl;

import com.oasis.data.dto.request.MemberCreateRequestDto;
import com.oasis.data.entity.Member;
import com.oasis.repository.MemberRepository;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberCreateRequestDto memberCreateRequestDto) {
        return memberRepository.save(memberCreateRequestDto.toMember());
    }

    @Override
    public void createMember(Member member) {
        memberRepository.save(member);
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
        // TODO :: exception 생각 
        return memberRepository.findById(sid).orElseThrow();
    }

    @Override
    public void deleteOneMember(Long sid) {
        memberRepository.deleteById(sid);
    }

}
