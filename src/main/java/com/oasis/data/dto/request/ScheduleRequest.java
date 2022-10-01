package com.oasis.data.dto.request;

import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private Long memberSid;

    @NotNull(message = "[name]은 null일 수 없습니다.")
    private String name;

    private String content;

    @NotNull(message = "[scheduleTypeSid]는 null일 수 없습니다.")
    private Long scheduleTypeSid;

    @NotNull(message = "[date]은 null일 수 없습니다.")
    private LocalDate date;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Schedule toSchedule() {
        return ModelMapperUtils.getModelMapper().map(this, Schedule.class);
    }
}
