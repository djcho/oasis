package com.oasis.data.entity;

import com.oasis.common.constant.MemberRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Email(message = "Invalid [id] format")
    @NotNull(message = "[id] cannot be null")
    @Column(unique = true)
    private String id;

    @NotNull(message = "[name] cannot be null")
    private String name;
    
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @ManyToOne
    @JoinColumn(name = "department_sid")
    private Department department;

    private String password;
    
    @OneToOne
    @JoinColumn(name = "work_position_sid")
    private WorkPosition workPosition;
    
    @OneToOne
    @JoinColumn(name = "work_duty_sid")
    private WorkDuty workDuty;

}
