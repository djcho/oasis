package com.oasis.controller;

import com.oasis.data.dto.request.UserRequestDto;
import com.oasis.data.entity.Member;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    
    /**
     * 전체 사용자 조회 (TODO :: 조회조건 필요)
     * */
    @GetMapping("/users")
    public ResponseEntity<List<Member>> getAllUsers(Pageable userPage) {
        return new ResponseEntity<>(memberService.getAllMembers(userPage), HttpStatus.OK);
    }
    
    /**
     * 특정 사용자 조회
     * */
    @GetMapping("/users/{userSid}")
    public ResponseEntity<Member> getOneUser(@PathVariable Long userSid) {
        return new ResponseEntity<>(memberService.getOneMember(userSid), HttpStatus.OK);
    }

    /**
     * 사용자 생성 
     * */
    // TODO :: 이하 롤체크 필요 
    @PostMapping("/users")
    public ResponseEntity<Member> createUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(memberService.createMember(userRequestDto), HttpStatus.OK);
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/users/{userSid}")
    public ResponseEntity deleteUser(@PathVariable Long userSid) {
        memberService.deleteOneMember(userSid);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
