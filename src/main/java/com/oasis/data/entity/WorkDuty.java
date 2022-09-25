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
public class WorkDuty {
    
    // TODO :: level을 둬야할까? 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    
    private String name;
    
}
