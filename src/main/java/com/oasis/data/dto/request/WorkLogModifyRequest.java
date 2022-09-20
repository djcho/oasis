package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkLogModifyRequest {
    private String content;

    @Override
    public String toString(){
        return "WorkLogModifyRequest[content=" + content + "]";
    }
}
