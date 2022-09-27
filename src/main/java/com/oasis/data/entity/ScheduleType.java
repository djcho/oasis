package com.oasis.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ScheduleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @NotNull(message = "[name] can not be null.")
    private String name;
}
