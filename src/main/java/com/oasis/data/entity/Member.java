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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Email(message = "Invalid [id] format")
    @NotNull(message = "[id] cannot be null")
    private String id;

    @NotNull(message = "[name] cannot be null")
    private String name;
    
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @ManyToOne
    @JoinColumn(name = "department_sid")
    private Department department;

    private String password;
    
    // user:position(many:one 단방향 매핑) 
    @ManyToOne
    @JoinColumn(name = "work_position_sid")
    private WorkPosition workPosition;
    
    @ManyToOne
    @JoinColumn(name = "work_duty_sid")
    private WorkDuty workDuty;

}
