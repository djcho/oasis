package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.common.exception.CommonException;
import com.oasis.data.dto.request.DepartmentRequest;
import com.oasis.data.dto.response.DepartmentResponse;
import com.oasis.data.entity.Department;
import com.oasis.repository.DepartmentRepository;
import com.oasis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> list() {
        return departmentRepository.findAll().stream()
                .map(d -> new DepartmentResponse(d.getSid(), d.getParentSid(), d.getName(), d.getLevel()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(DepartmentRequest dto){
        Department parent = departmentRepository.findById(dto.getParentSid()).orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
        Department department = Department.builder()
                .name(dto.getName())
                .parentSid(dto.getParentSid())
                .level(parent.getLevel() + 1)
                .build();
        departmentRepository.save(department);
    }
    
    public void saveAll(List<Department> departments) {
        departmentRepository.saveAll(departments);
    }
    
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
    
    @Transactional
    public void update(Long sid, DepartmentRequest dto) {
        Department current = departmentRepository.findById(sid).orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
        Department parent = departmentRepository.findById(dto.getParentSid()).orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
        if(current.getParentSid() == dto.getParentSid() && current.getName().equals(dto.getName())) {
            return;
        }

        if(current.getName().equals(dto.getName())) {
            current.setParentSid(dto.getParentSid());
            current.setLevel(parent.getLevel() + 1);
        }
        if(current.getParentSid() == dto.getParentSid()) {
            current.setName(dto.getName());
        }
        departmentRepository.save(current);
    }

    @Transactional
    public void delete(Long sid) {
        departmentRepository.deleteById(sid);
    }

    public DepartmentResponse detail(Long sid) {
        return departmentRepository.findById(sid)
                .map(d -> new DepartmentResponse(d.getSid(), d.getParentSid(), d.getName(), d.getLevel()))
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
    }
}
