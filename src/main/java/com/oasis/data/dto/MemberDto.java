package com.oasis.data.dto;

import com.oasis.common.constant.MemberRole;
import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long sid;
    private String id;
    private String name;
    private MemberRole memberRole;
    private DepartmentEntity department;
    private WorkPositionEntity workPositionEntity;
    private WorkDutyEntity workDutyEntity;
    

    public static MemberDto of(MemberEntity member) {
        return ModelMapperUtils.getModelMapper().map(member, MemberDto.class);
    }
    
    public MemberEntity toMember() {
        return ModelMapperUtils.getModelMapper().map(this, MemberEntity.class);
    }

}
