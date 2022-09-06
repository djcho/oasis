package com.oasis.controller;

import com.oasis.data.entity.User;
import com.oasis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(Pageable userPage) {
        return new ResponseEntity<>(userService.getAllUsers(userPage), HttpStatus.OK);
    }
    
    @GetMapping("/users/{userSid}")
    public ResponseEntity<User> getOneUser(@PathVariable Long userSid) {
        return new ResponseEntity<>(userService.getOneUser(userSid), HttpStatus.OK);
    }
}
