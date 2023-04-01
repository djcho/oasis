package com.oasis.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "schedule" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "member_id", "schedule_type_id"})})
public class ScheduleEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    //1:1 단방향 관계, ScheduleType 에선 Schedule 을 참조할 필요가 없음
    // JoinColumn 로 외래키 확보
    @OneToOne
    @JoinColumn(name = "schedule_type_id")
    ScheduleTypeEntity scheduleType;

    //N:1 단방향 관계, 관계의 주인으로 지정
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
