package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oasis_department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
    private Long parentSid;
    private String name;
    private int level;
    private LocalDateTime createDttm;
    private LocalDateTime modifyDttm;
}
