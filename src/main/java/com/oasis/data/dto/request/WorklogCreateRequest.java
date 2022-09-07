package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorklogCreateRequest {
    private Long userSid;
    private String content;
    private LocalDateTime workingDate;

    @Override
    public String toString(){
        return "WorklogCreateRequest[userSid=" + userSid + "content=" + content + "workingDate" + workingDate + "]";
    }
}
