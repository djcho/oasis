package com.oasis.common.exception;

import com.oasis.controller.ScheduleController;
import com.oasis.data.dto.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.oasis.common.constant.ErrorCode.DUPLICATION_RESOURCE;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<CommonResponse> dataDuplicationExceptionHandler() {
        LOGGER.error("dataDuplicationExceptionHandler");
        return CommonResponse.toResponseEntity(DUPLICATION_RESOURCE);
    }

    @ExceptionHandler(value = {CommonException.class})
    protected  ResponseEntity<CommonResponse> commonExceptionHandler(CommonException ex){
        LOGGER.error("commonExceptionHandler, exception : {}", ex);
        return CommonResponse.toResponseEntity(ex.getErrorCode());
    }
}
