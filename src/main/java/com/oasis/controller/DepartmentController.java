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

    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponse> update(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequestDto) {
        departmentService.update(id, departmentRequestDto);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return CommonResponse.toResponseEntity(ErrorCode.SUCCESS);
    }

    @GetMapping("/{id}")
    public DepartmentResponse detail(@PathVariable Long id) {
        return departmentService.detail(id);
    }


}
