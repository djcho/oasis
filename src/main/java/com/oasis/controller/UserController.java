package com.oasis.controller;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.User;
import com.oasis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    /**
     * 전체 사용자 조회 (TODO :: 조회조건 필요)
     * */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable userPage) {
        return new ResponseEntity<>(userService.getAllUsers(userPage), HttpStatus.OK);
    }
    
    /**
     * 특정 사용자 조회
     * */
    @GetMapping("/users/{userSid}")
    public ResponseEntity<User> getOneUser(@PathVariable Long userSid) {
        return new ResponseEntity<>(userService.getOneUser(userSid), HttpStatus.OK);
    }

    /**
     * 사용자 생성 
     * */
    // TODO :: 이하 롤체크 필요 
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.OK);
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/users/{userSid}")
    public ResponseEntity deleteUser(@PathVariable Long userSid) {
        userService.deleteOneUser(userSid);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
