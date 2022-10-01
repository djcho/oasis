package com.oasis.controller;

import com.oasis.data.dto.request.MemberCreateRequestDto;
import com.oasis.data.entity.Member;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    
    /**
     * 전체 사용자 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/users")
    public ResponseEntity<List<Member>> getAllMembers(
            @PageableDefault(size = 50) 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "department"),
                @SortDefault(sort = "workDuty"),
                @SortDefault(sort = "workPosition")}) Pageable userPage) {
        return new ResponseEntity<>(memberService.getAllMembers(userPage), HttpStatus.OK);
    }
    
    /**
     * 특정 사용자 조회
     * */
    @GetMapping("/users/{userSid}")
    public ResponseEntity<Member> getOneMember(@PathVariable Long userSid) {
        return new ResponseEntity<>(memberService.getOneMember(userSid), HttpStatus.OK);
    }

    /**
     * 사용자 생성 
     * */
    // TODO :: 이하 롤체크 필요 
    @PostMapping("/users")
    public ResponseEntity<Member> createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return new ResponseEntity<>(memberService.createMember(memberCreateRequestDto), HttpStatus.OK);
    }
    
    /**
     * 사용자 비밀번호 수정
     * */
    public ResponseEntity<Member> updatePassword() {
        return null;
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/users/{userSid}")
    public ResponseEntity deleteMember(@PathVariable Long userSid) {
        memberService.deleteOneMember(userSid);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
