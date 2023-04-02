package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "member")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
    
    @OneToOne
    @JoinColumn(name = "work_position_id")
    private WorkPositionEntity workPosition;
    
    @OneToOne
    @JoinColumn(name = "work_duty_id")
    private WorkDutyEntity workDuty;
}
