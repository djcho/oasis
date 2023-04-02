package com.oasis.controller.rest;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.ResponseDtoTemplate;
import com.oasis.data.dto.request.CreateMemberRequestDto;
import com.oasis.data.dto.request.MemberChangePasswordRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.exception.CommonException;
import com.oasis.service.InvitationService;
import com.oasis.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final InvitationService invitationService;

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
    public ResponseEntity<ResponseDtoTemplate<MemberDto>> getMember(@PathVariable Long memberSid) {
        return ResponseDtoTemplate.toResponseEntity(ErrorCode.SUCCESS, memberService.getMember(memberSid));
    }

    @PostMapping
    @Operation(summary = "회원 추가", description = "새로운 회원을 추가합니다.")
    public ResponseEntity<ResponseDtoTemplate<MemberDto>> createMember(
            @RequestBody CreateMemberRequestDto createMemberRequestDto,
            @RequestParam(name = "token") String signUpToken) {
        if (!invitationService.validateInvitationToken(signUpToken)) {
            throw new CommonException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
        return ResponseDtoTemplate.toResponseEntity(ErrorCode.SUCCESS, memberService.createMember(signUpToken, createMemberRequestDto));
    }
    
    /**
     * 사용자 비밀번호 수정
     * */
    @PatchMapping("/{memberSid}/password")
    @Operation(summary = "회원 비밀번호 수정", description = "회원 비밀번호를 변경합니다.")
    public ResponseEntity<ResponseDtoTemplate<MemberDto>> changePassword(@RequestBody MemberChangePasswordRequest memberChangePasswordRequest) {
        return ResponseDtoTemplate.toResponseEntity(ErrorCode.SUCCESS, memberService.changePassword(memberChangePasswordRequest));
    }

    /**
     * 사용자 삭제 
     * */
    @DeleteMapping("/{memberSid}")
    public ResponseEntity<CommonResponse> deleteMember(@PathVariable Long memberSid) {
        memberService.deleteMember(memberSid);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }
    
    
}
