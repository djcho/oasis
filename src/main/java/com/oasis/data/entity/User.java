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
public class User extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
    
    @Email
    @NotNull
    private String id;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String name;
    
    private String password;
    
}
