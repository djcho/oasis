package com.oasis.service;

import com.oasis.data.dto.request.DepartmentRequest;
import com.oasis.data.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    void save(DepartmentRequest departmentRequestDto);
    List<DepartmentResponse> list();
    void update(Long sid, DepartmentRequest departmentRequestDto);
    void delete(Long sid);
    DepartmentResponse detail(Long sid);
}
