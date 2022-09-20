package com.oasis.data.entity;

import com.oasis.common.constant.MemberRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Email(message = "Invalid [id] format")
    @NotNull(message = "[id] cannot be null")
    private String id;
    
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @NotNull(message = "[name] cannot be null")
    private String name;

    private String password;
    
    // user:position(many:one 단방향 매핑) 
    @ManyToOne
    @JoinColumn(name = "work_position_sid")
    private WorkPosition workPosition;

}
