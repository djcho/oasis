package com.oasis.service.impl;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.Member;
import com.oasis.repository.UserRepository;
import com.oasis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    public Member createUser(UserRequestDto userRequestDto) {
        return userRepository.save(userRequestDto.toUser());
    }

    @Override
    public List<Member> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public Member getOneUser(Long sid) {
        // TODO :: exception 생각 
        return userRepository.findById(sid).orElseThrow();
    }

    @Override
    public void deleteOneUser(Long sid) {
        userRepository.deleteById(sid);
    }

}
