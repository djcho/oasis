package com.oasis.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    /* 200 OK : 성공 */
    SUCCESS(OK, "성공"),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_PARAM(BAD_REQUEST, "유효하지 않은 입력 입니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED, "인가되지 않은 사용자 입니다."),

    /* 404 NOT_FOUND : 리소스가 존재하지 않음 */
    NOT_FOUND_USER(NOT_FOUND, "사용자를 찾을 수 없습니다."),
    NOT_FOUND_DEPARTMENT(NOT_FOUND, "부서를 찾을 수 없습니다."),
    NOT_FOUND_SCHEDULE(NOT_FOUND, "일정을 찾을 수 없습니다."),
    NOT_FOUND_ATTENDANCE(NOT_FOUND, "출퇴근 정보를 찾을 수 없습니다."),
    NOT_FOUND_WORK_LOG(NOT_FOUND, "업무 일지를 찾을 수 없습니다."),

    /* 409 CONFLICT : 다른 리소스와 충돌함, 중복된 데이터 */
    DUPLICATION_USER(CONFLICT, "이미 동일한 사용자가 존재합니다."),
    DUPLICATION_RESOURCE(CONFLICT, "이미 동일한 리소스가 존재합니다."),
        ;

    HttpStatus httpStatus;
    String message;

    private ErrorCode(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
