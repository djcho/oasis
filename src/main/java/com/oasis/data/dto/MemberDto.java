package com.oasis.data.dto;

import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String uid;
    private String name;
    private Set<RoleEntity> roles;
    private DepartmentEntity department;
    private WorkPositionEntity workPosition;
    private WorkDutyEntity workDuty;
    

    public static MemberDto of(MemberEntity member) {
        return ModelMapperUtils.getModelMapper().map(member, MemberDto.class);
    }
    
    public MemberEntity toMember() {
        return ModelMapperUtils.getModelMapper().map(this, MemberEntity.class);
    }

}
