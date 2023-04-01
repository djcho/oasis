package com.oasis.data.dto.request;

import com.oasis.data.entity.MemberEntity;
import com.oasis.data.entity.ScheduleEntity;
import com.oasis.data.entity.ScheduleTypeEntity;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleRequest {
    private Long memberId;

    @NotNull(message = "[name]은 null일 수 없습니다.")
    private String name;

    private String content;

    @NotNull(message = "[scheduleTypeSid]는 null일 수 없습니다.")
    private Long scheduleTypeSid;

    @NotNull(message = "[date]은 null일 수 없습니다.")
    private LocalDate date;

    public ScheduleEntity toSchedule(MemberEntity memberEntity, ScheduleTypeEntity scheduleType) {
        PropertyMap<ScheduleRequest, ScheduleEntity> personMap = new PropertyMap<>() {
            protected void configure() {
                map().setId(null);
                map().setMember(memberEntity);
                map().setScheduleType(scheduleType);
            }
        };
        ModelMapper mm = new ModelMapper();
        mm.addMappings(personMap);
        return mm.map(this, ScheduleEntity.class);
    }
}
