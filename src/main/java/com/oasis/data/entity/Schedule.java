package com.oasis.data.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "[name]can not be null")
    private String name;

    @Nullable
    private String content;

    @NotNull(message = "[date] can not be null")
    private LocalDate date;

    //1:1 단방향 관계, ScheduleType 에선 Schedule 을 참조할 필요가 없음
    // JoinColumn 로 외래키 확보
    @OneToOne
    @JoinColumn(name = "schedule_sid")
    @NotNull(message = "[scheduleType] can not be null")
    ScheduleType scheduleType;

    //N:1 단방향 관계, 관계의 주인으로 지정
    @ManyToOne
    @JoinColumn(name = "member_sid")
    @ToString.Exclude
    @NotNull(message = "[member] can not be null")
    private Member member;
}
