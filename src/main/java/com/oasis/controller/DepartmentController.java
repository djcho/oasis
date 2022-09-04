package com.oasis.controller;

import com.oasis.data.dto.request.DepartmentRequestDto;
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
    public List<DepartmentRequestDto> list() {

        return departmentService.list();
    }
    

    @PostMapping
    public void save(@RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentService.save(departmentRequestDto);
    }

    @PatchMapping("/{deptsid}")
    public void update(@PathVariable Long deptSid, @RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentService.update(deptSid, departmentRequestDto);
    }

    @DeleteMapping("/{deptSid}")
    public void delete(@PathVariable Long deptSid) {
        departmentService.delete(deptSid);

    }

    @GetMapping("/{deptSid}")
    public DepartmentRequestDto detail(@PathVariable Long deptSid) {
        return departmentService.detail(deptSid);
    }


}
