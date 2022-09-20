package com.oasis.data.dto.response;

import com.oasis.common.constant.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Builder
public class CommonResponse<T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final T data;

    public static <T> ResponseEntity<CommonResponse> toResponseEntity(ErrorCode errorCode, @Nullable T data) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .data(data)
                        .build());
    }

    public static ResponseEntity<CommonResponse> toResponseEntity(ErrorCode errorCode) {
        return CommonResponse.toResponseEntity(errorCode, null);
    }
}
