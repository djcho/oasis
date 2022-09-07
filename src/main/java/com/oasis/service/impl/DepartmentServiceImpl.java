package com.oasis.service.impl;

import com.oasis.data.dto.request.DepartmentRequestDto;
import com.oasis.data.entity.Department;
import com.oasis.repository.DepartmentRepository;
import com.oasis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentRequestDto> list() {
        List<Department> dd =  departmentRepository.findAll();
        return departmentRepository.findAll().stream().map(d -> new DepartmentRequestDto(d.getSid(), d.getParentSid(), d.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void save(DepartmentRequestDto dto){
        Department parent = departmentRepository.findById(dto.getParentSid()).orElseThrow(RuntimeException::new);
        Department department = Department.builder()
                .name(dto.getName())
                .parentSid(dto.getParentSid())
                .level(parent.getLevel() + 1)
                .build();
        departmentRepository.save(department);
    }
    @Transactional
    public void update(Long sid, DepartmentRequestDto dto) {
        Department current = departmentRepository.findById(sid).orElseThrow(RuntimeException::new);
        Department parent = departmentRepository.findById(dto.getParentSid()).orElseThrow(RuntimeException::new);
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
        current.setUpdatedAt(LocalDateTime.now());

        departmentRepository.save(current);
    }

    @Transactional
    public void delete(Long sid) {
        departmentRepository.deleteById(sid);
    }

    public DepartmentRequestDto detail(Long sid) {
        return departmentRepository.findById(sid).map(d -> new DepartmentRequestDto(d.getSid(), d.getParentSid(), d.getName())).orElseThrow(RuntimeException::new);
    }
}
