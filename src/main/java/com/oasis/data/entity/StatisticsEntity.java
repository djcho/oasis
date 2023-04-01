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
@Table(name = "statistics")
public class StatisticsEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private long workingDayCount;

    @Column(nullable = false)
    private long officeWorkCount;

    @Column(nullable = false)
    private long remoteWorkCount;

    @Column(nullable = false)
    private long dayOffCount;

    @Column(nullable = false)
    private long halfDayOffCount;

    @Column(nullable = false)
    private double officeWorkRate;

    @Column(nullable = false)
    private double remoteWorkRate;

    @Column(nullable = false)
    private double dayOffRate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
