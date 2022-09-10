package com.oasis.service;

import com.oasis.data.dto.request.DepartmentRequestDto;
import com.oasis.data.dto.response.DepartmentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
    void save(DepartmentRequestDto departmentRequestDto);
    List<DepartmentResponseDto> list();
    void update(Long sid, DepartmentRequestDto departmentRequestDto);
    void delete(Long sid);
    DepartmentResponseDto detail(Long sid);
}
