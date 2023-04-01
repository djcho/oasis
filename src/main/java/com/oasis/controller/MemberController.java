package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    
    private final MemberService memberService;
    
    /**
     * 전체 사용자 조회 (TODO :: 검색 조건 추가 필요)
     * */
    @GetMapping
    public ResponseEntity<CommonResponse> getAllMembers(
            @PageableDefault(size = 50) 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "department"),
                @SortDefault(sort = "workDuty"),
                @SortDefault(sort = "workPosition")}) Pageable userPage) {
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, memberService.getAllMembers(userPage));
    }
    
    /**
     * 특정 사용자 조회
     * */
    @GetMapping("/{memberSid}")
    public ResponseEntity<CommonResponse> getOneMember(@PathVariable Long memberSid) {
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, memberService.getOneMember(memberSid));
    }

    /**
     * 사용자 생성 
     * */
    // TODO :: 이하 롤체크 필요 !!!
    @PostMapping
    public ResponseEntity<CommonResponse> createMember(@RequestBody MemberCreationRequest memberCreationRequest) {
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, memberService.createMember(memberCreationRequest));
    }
    
    /**
     * 사용자 비밀번호 수정
     * */
    @PatchMapping("/{memberSid}/password")
    public ResponseEntity<CommonResponse> updatePassword(@RequestBody MemberChangePasswordRequest memberChangePasswordRequest) {
        memberService.changePassword(memberChangePasswordRequest);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/{memberSid}")
    public ResponseEntity<CommonResponse> deleteMember(@PathVariable Long memberSid) {
        memberService.deleteOneMember(memberSid);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }
    
    
}
