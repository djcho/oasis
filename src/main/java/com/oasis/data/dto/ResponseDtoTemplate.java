package com.oasis.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oasis.common.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseDtoTemplate<T> {
    private final String timestamp;
    private final String status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public ResponseDtoTemplate(){
        this.timestamp = "";
        this.status = "";
        this.code = 0;
        this.message= "";
        this.data = null;
    }
    public ResponseDtoTemplate(String timestamp, String status, int code, String message, T data){
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;

        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseDtoTemplate<T>> toResponseEntity(ErrorCode errorCode, @Nullable T data) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseDtoTemplate.<T>builder()
                        .timestamp(LocalDateTime.now().toString())
                        .status(errorCode.getHttpStatus().name())
                        .code(errorCode.getHttpStatus().value())
                        .message(errorCode.getMessage())
                        .data(data)
                        .build());
    }

    public static ResponseEntity<?> toResponseEntity(ErrorCode errorCode) {
        return ResponseDtoTemplate.toResponseEntity(errorCode, null);
    }
}

