package com.oasis.service;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    
    Member createUser(UserRequestDto userRequestDto);
    List<Member> getAllUsers(Pageable pageable);
    Member getOneUser(Long sid);
    void deleteOneUser(Long sid);
    
}
