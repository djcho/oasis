package com.oasis.exception;

import com.szs.ct.data.dto.ResponseDtoTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.szs.ct.constant.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String exceptionMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseDtoTemplate.toResponseEntity(INVALID_PARAM, exceptionMessage);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    protected ResponseEntity<?> methodArgumentNotValidException(MissingServletRequestParameterException ex) {
        return ResponseDtoTemplate.toResponseEntity(INVALID_PARAM, ex.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        String exceptionMessage = ex.getLocalizedMessage();
        return ResponseDtoTemplate.toResponseEntity(DATA_INTEGRITY_VIOLATION, exceptionMessage);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<?> constraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            sb.append(violation.getMessage().concat(";"));
        }
        return ResponseDtoTemplate.toResponseEntity(INVALID_PARAM, sb.toString());
    }

    @ExceptionHandler(value = {CommonException.class})
    protected  ResponseEntity<?> commonExceptionHandler(CommonException ex){
        return ResponseDtoTemplate.toResponseEntity(ex.getErrorCode());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected  ResponseEntity<?> runtimeExceptionHandler(RuntimeException ex){
        System.out.println(ex.toString());
        return ResponseDtoTemplate.toResponseEntity(NOT_DEFINITION_ERROR);
    }
}
