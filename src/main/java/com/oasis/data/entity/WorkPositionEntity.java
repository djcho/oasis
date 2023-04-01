package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "work_position", uniqueConstraints = {@UniqueConstraint(columnNames = {"level", "name"})})
public class WorkPositionEntity extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long level;

    @Column(nullable = false)
    private String name;
    
}
