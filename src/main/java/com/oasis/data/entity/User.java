package com.oasis.data.entity;

import com.oasis.common.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oasis_user")
public class User extends BaseEntity {

    @Email(message = "Invalid [id] format")
    @NotNull(message = "[id] cannot be null")
    private String id;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "[name] cannot be null")
    private String name;
    
    private String password;
    
    // user:position(many:one 단방향 매핑) 
    @ManyToOne
    @JoinColumn(name = "sid")
    private Position position;
    
}
