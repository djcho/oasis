package com.oasis.service;

import com.oasis.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    public List<User> getAllUsers(Pageable pageable);
    
    public User getOneUser(Long sid);
    
}
