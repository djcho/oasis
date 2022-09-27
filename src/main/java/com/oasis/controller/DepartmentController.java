package com.oasis.controller;

import com.oasis.data.dto.request.DepartmentRequest;
import com.oasis.data.dto.response.DepartmentResponse;
import com.oasis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depts")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponse> list() {

        return departmentService.list();
    }

    @PostMapping
    public void save(@RequestBody DepartmentRequest departmentRequestDto) {
        departmentService.save(departmentRequestDto);
    }

    @PatchMapping("/{deptSid}")
    public void update(@PathVariable Long deptSid, @RequestBody DepartmentRequest departmentRequestDto) {
        departmentService.update(deptSid, departmentRequestDto);
    }

    @DeleteMapping("/{deptSid}")
    public void delete(@PathVariable Long deptSid) {
        departmentService.delete(deptSid);

    }

    @GetMapping("/{deptSid}")
    public DepartmentResponse detail(@PathVariable Long deptSid) {
        return departmentService.detail(deptSid);
    }


}
