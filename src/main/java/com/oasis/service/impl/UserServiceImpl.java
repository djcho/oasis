package com.oasis.service.impl;

import com.oasis.data.entity.User;
import com.oasis.repository.UserRepository;
import com.oasis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public User getOneUser(Long sid) {
        // TODO :: exception 생각 
        return userRepository.findById(sid).orElseThrow();
    }

}
