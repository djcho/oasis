package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.common.exception.CommonException;
import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.dto.request.DepartmentRequest;
import com.oasis.data.dto.response.DepartmentResponse;
import com.oasis.data.entity.Department;
import com.oasis.repository.DepartmentRepository;
import com.oasis.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        Department parent = departmentRepository.findById(dto.getParentSid())
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
        Department department = Department.builder()
                .name(dto.getName())
                .parentSid(dto.getParentSid())
                .level(parent.getLevel() + 1)
                .build();
        departmentRepository.save(department);
    }

    @Transactional
    public void saveAll(List<Department> departments) {
        departmentRepository.saveAll(departments);
    }

    @Transactional
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
    
    @Transactional
    public void update(Long sid, DepartmentRequest dto) {
        // dept id로 현재 부서값 가져오기
        Department current = departmentRepository.findById(sid)
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
        // parent dept id로 옮겨질 부서 값 가져오기
        Department parent = departmentRepository.findById(dto.getParentSid())
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        // 현재 부서의 부모와 옮겨질 부모가 같고, 이름도 변경된게 없으면 그냥 종료
        if(current.getParentSid() == dto.getParentSid()
                && current.getName().equals(dto.getName())) {
            return;
        }
        // 이름이 변경 되지 않았다면 옮겨질 부서의 레벨값 + 1
        if(!current.getName().equals(dto.getName())) {
            current.setName(dto.getName());

        }
        //이름만 변견됐다면 이름만 바꾸기
        if(!(current.getParentSid() == dto.getParentSid())) {
            current.setParentSid(dto.getParentSid());
            current.setLevel(parent.getLevel() + 1);
        }
        departmentRepository.save(current);
    }

    @Transactional
    public void delete(Long sid) {
        departmentRepository.deleteById(sid);
    }

    public DepartmentResponse detail(Long sid) {
        ModelMapper mapper = ModelMapperUtils.getModelMapper();
        return departmentRepository.findById(sid)
                .map(d -> mapper.map(d, DepartmentResponse.class))
                .orElseThrow(()-> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));
    }
}
