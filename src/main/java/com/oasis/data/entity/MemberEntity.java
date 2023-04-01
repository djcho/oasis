package com.oasis.data.entity;

import com.oasis.common.constant.MemberRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "member")
public class MemberEntity extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid [id] format")
    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @Column(nullable = false)
    private String password;
    
    @OneToOne
    @JoinColumn(name = "work_position_id")
    private WorkPositionEntity workPosition;
    
    @OneToOne
    @JoinColumn(name = "work_duty_id")
    private WorkDutyEntity workDuty;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
