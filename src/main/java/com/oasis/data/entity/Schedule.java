package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    private String name;
    private String content;
    private LocalDate date;

    //N:1 양방향 관계, 관계의 주인으로 지정
    @ManyToOne
    @JoinColumn(name = "member_sid")
    @ToString.Exclude
    private Member member;
}
