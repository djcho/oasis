package com.oasis.data.dto.request;

import com.oasis.common.constant.MemberRole;
import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberReqeustDto {
    
    private Long id;
    private String uid;
    private String name;
    private MemberRole memberRole;
    private Long departmentSid;
    private String password;
    private Long workPositionSid;
    private Long workDutySid;
    
    public MemberEntity toMemberEntity() {
        return ModelMapperUtils.getModelMapper().map(this, MemberEntity.class);
    }
    
}
