package com.oasis.common.exception;

import com.oasis.common.constant.ErrorCode;
import com.oasis.controller.ScheduleController;
import com.oasis.data.dto.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static com.oasis.common.constant.ErrorCode.DATA_INTEGRITY_VIOLATION;
import static com.oasis.common.constant.ErrorCode.INVALID_PARAM;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.error("dataDuplicationExceptionHandler");
        ErrorCode errorCode = INVALID_PARAM;
        return CommonResponse.toResponseEntity(errorCode, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<CommonResponse> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        LOGGER.error("dataDuplicationExceptionHandler");
        ErrorCode errorCode = DATA_INTEGRITY_VIOLATION;
        return CommonResponse.toResponseEntity(errorCode, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<CommonResponse> constraintViolationException(ConstraintViolationException ex) {
        LOGGER.error("constraintViolationExceptionHandler");
        ErrorCode errorCode = INVALID_PARAM;
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            sb.append(violation.getMessage().concat(";"));
        }
        return CommonResponse.toResponseEntity(errorCode, sb);
    }

    @ExceptionHandler(value = {CommonException.class})
    protected  ResponseEntity<CommonResponse> commonExceptionHandler(CommonException ex){
        LOGGER.error("commonExceptionHandler, exception : {}", ex);
        return CommonResponse.toResponseEntity(ex.getErrorCode());
    }
}
