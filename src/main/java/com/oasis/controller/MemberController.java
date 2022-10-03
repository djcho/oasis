package com.oasis.controller;

import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
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
@RequestMapping("/api/v1")
public class MemberController {
    
    private final MemberService memberService;
    
    /**
     * 전체 사용자 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping("/members")
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
    @GetMapping("/members/{memberSid}")
    public ResponseEntity<Member> getOneMember(@PathVariable Long memberSid) {
        return new ResponseEntity<>(memberService.getOneMember(memberSid), HttpStatus.OK);
    }

    /**
     * 사용자 생성 
     * */
    // TODO :: 이하 롤체크 필요 !!!
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest memberCreationRequest) {
        return new ResponseEntity<>(memberService.createMember(memberCreationRequest), HttpStatus.OK);
    }
    
    /**
     * 사용자 비밀번호 수정
     * */
    @PatchMapping("/members/password")
    public ResponseEntity<Member> updatePassword(@RequestBody MemberChangePasswordRequest memberChangePasswordRequest) {
        memberService.changePassword(memberChangePasswordRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/members/{memberSid}")
    public ResponseEntity deleteMember(@PathVariable Long memberSid) {
        memberService.deleteOneMember(memberSid);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
