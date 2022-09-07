package com.oasis.service;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    
    User createUser(UserRequestDto userRequestDto);
    List<User> getAllUsers(Pageable pageable);
    User getOneUser(Long sid);
    void deleteOneUser(Long sid);
    
}
