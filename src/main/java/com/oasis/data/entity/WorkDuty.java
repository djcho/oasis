package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDuty {
    
    // TODO :: level을 둬야할까? 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    
    private String name;
    
}
