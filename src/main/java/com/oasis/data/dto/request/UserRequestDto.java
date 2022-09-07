package com.oasis.data.dto.request;

import com.oasis.common.constant.Role;
import com.oasis.data.entity.User;
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
    private Role role;
    private String password;
    private WorkPosition workPosition;
    
    public User toUser(){
        return User.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .password(this.password)
                .workPosition(this.workPosition)
                .build();
    }
}
