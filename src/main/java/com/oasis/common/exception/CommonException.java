package com.oasis.common.exception;

import com.oasis.common.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private ErrorCode errorCode;
}
