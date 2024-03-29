package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkLogCreateRequest {
    private Long memberId;
    private String content;
    private LocalDateTime workingDate;

    @Override
    public String toString(){
        return "WorkLogCreateRequest[userSid=" + memberId + "content=" + content + "workingDate" + workingDate + "]";
    }
}
