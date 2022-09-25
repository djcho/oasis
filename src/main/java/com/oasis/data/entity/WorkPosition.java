package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"level", "name"})})
public class WorkPosition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    
    private Long level;
    
    private String name;
    
}
