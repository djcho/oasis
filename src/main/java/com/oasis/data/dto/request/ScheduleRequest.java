package com.oasis.data.dto.request;

import com.oasis.common.util.ModelMapperUtils;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.Schedule;
import com.oasis.data.entity.ScheduleType;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleRequest {
    private Long memberSid;

    @NotNull(message = "[name]은 null일 수 없습니다.")
    private String name;

    private String content;

    @NotNull(message = "[scheduleTypeSid]는 null일 수 없습니다.")
    private Long scheduleTypeSid;

    @NotNull(message = "[date]은 null일 수 없습니다.")
    private LocalDate date;

    public Schedule toSchedule(Member member, ScheduleType scheduleType) {
        PropertyMap<ScheduleRequest, Schedule> personMap = new PropertyMap<>() {
            protected void configure() {
                map().setSid(null);
                map().setMember(member);
                map().setScheduleType(scheduleType);
            }
        };
        ModelMapper mm = new ModelMapper();
        mm.addMappings(personMap);
        return mm.map(this, Schedule.class);
    }
}
