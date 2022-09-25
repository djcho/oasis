package com.oasis.service;

import com.oasis.data.dto.request.DepartmentRequestDto;
import com.oasis.data.dto.response.DepartmentResponseDto;
import com.oasis.data.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
    void save(DepartmentRequestDto departmentRequestDto);
    void saveAll(List<Department> departments);
    List<DepartmentResponseDto> list();
    void update(Long sid, DepartmentRequestDto departmentRequestDto);
    void delete(Long sid);
    void deleteAll();
    DepartmentResponseDto detail(Long sid);
}
