package com.oasis.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oasis_position", uniqueConstraints = {@UniqueConstraint(columnNames = {"level", "name"})})
public class Position {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
    
    private Long level;
    
    private String name; 
    
}
