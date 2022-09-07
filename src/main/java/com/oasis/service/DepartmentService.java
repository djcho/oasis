package com.oasis.service;

import com.oasis.data.dto.request.DepartmentRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    void save(DepartmentRequestDto departmentRequestDto);
    List<DepartmentRequestDto> list();
    void update(Long sid, DepartmentRequestDto departmentRequestDto);
    void delete(Long sid);
    DepartmentRequestDto detail(Long sid);
}
