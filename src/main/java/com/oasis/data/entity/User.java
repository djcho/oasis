package com.oasis.data.entity;

import com.oasis.common.constant.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oasis_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

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

    //N:1 양방향 관계, 관계 주인 : Schedule entity
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Schedule> scheduleList = new ArrayList<>();
}
