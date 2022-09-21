package com.oasis.data.dto.request;

import com.oasis.common.constant.MemberRole;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.WorkPosition;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    
    private String id;
    private String name;
    private MemberRole memberRole;
    private String password;
    private WorkPosition workPosition;
    
    public Member toUser(){
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .memberRole(this.memberRole)
                .password(this.password)
                .workPosition(this.workPosition)
                .build();
    }
}
