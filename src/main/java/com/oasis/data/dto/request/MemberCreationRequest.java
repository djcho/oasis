package com.oasis.data.dto.request;

import com.oasis.common.constant.MemberRole;
import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.Department;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.WorkDuty;
import com.oasis.data.entity.WorkPosition;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreationRequest {
    
    private Long sid;
    private String id;
    private String name;
    private MemberRole memberRole;
    private Long departmentSid;
    private String password;
    private Long workPositionSid;
    private Long workDutySid;
    
    public Member toMember() {
        return ModelMapperUtils.getModelMapper().map(this, Member.class);
    }
    
}
