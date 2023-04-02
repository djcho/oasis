package com.oasis.service.impl;

import com.oasis.data.entity.MemberEntity;
import com.oasis.repository.MemberRepository;
import com.oasis.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.oasis.common.constant.ErrorCode.NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUid(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MEMBER.getMessage()));
        return UserPrincipal.create(member);
    }
}
