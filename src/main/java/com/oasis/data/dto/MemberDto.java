package com.oasis.data.dto;

import com.oasis.common.constant.MemberRole;
import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.Department;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.WorkDuty;
import com.oasis.data.entity.WorkPosition;
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
    private Department department;
    private WorkPosition workPosition;
    private WorkDuty workDuty;
    

    public static MemberDto of(Member member) {
        return ModelMapperUtils.getModelMapper().map(member, MemberDto.class);
    }
    
    public Member toMember() {
        return ModelMapperUtils.getModelMapper().map(this, Member.class);
    }

}
