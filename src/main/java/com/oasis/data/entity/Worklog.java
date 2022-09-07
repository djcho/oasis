package com.oasis.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Worklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @NotNull
    private Long userSid;

    private LocalDateTime createDttm;

    private LocalDateTime modifyDttm;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime workingDate;
}
