package com.oasis.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorklogModifyRequest {
    private String content;

    @Override
    public String toString(){
        return "WorklogModifyRequest[content=" + content + "]";
    }
}
