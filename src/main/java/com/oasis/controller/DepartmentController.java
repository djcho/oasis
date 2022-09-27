package com.oasis.controller;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.dto.request.DepartmentRequest;
import com.oasis.data.dto.response.CommonResponse;
import com.oasis.data.dto.response.DepartmentResponse;
import com.oasis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depts")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<CommonResponse> list() {
        List<DepartmentResponse> responses = departmentService.list();
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS, responses);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> save(@RequestBody DepartmentRequest departmentRequestDto) {
        departmentService.save(departmentRequestDto);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @PatchMapping("/{deptSid}")
    public ResponseEntity<CommonResponse> update(@PathVariable Long deptSid, @RequestBody DepartmentRequest departmentRequestDto) {
        departmentService.update(deptSid, departmentRequestDto);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @DeleteMapping("/{deptSid}")
    public ResponseEntity<CommonResponse> delete(@PathVariable Long deptSid) {
        departmentService.delete(deptSid);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @GetMapping("/{deptSid}")
    public DepartmentResponse detail(@PathVariable Long deptSid) {
        return departmentService.detail(deptSid);
    }


}
