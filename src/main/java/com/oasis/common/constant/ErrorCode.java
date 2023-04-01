package com.oasis.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    /* 200 OK : 성공 */
    SUCCESS(OK, "성공"),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_PARAM(BAD_REQUEST, "유효하지 않은 입력입니다."),
    DATA_INTEGRITY_VIOLATION(BAD_REQUEST, "데이터 무결성에 위배되는 요청입니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_ACCESS_TOKEN(UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    INCORRECT_PASSWORD(UNAUTHORIZED, "유효하지 않은 패스워드입니다."),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "인가되지 않은 사용자 입니다."),

    /* 403 FORBIDDEN : 리소스에 접근할 권한이 없음 */
    ACCESS_DENIED(FORBIDDEN, "리소스에 접근할 권한이 없습니다."),

    /* 404 NOT_FOUND : 리소스가 존재하지 않음 */
    NOT_FOUND_MEMBER(NOT_FOUND, "사용자를 찾을 수 없습니다."),
    NOT_FOUND_SCRAP(NOT_FOUND, "스크랩을 찾을 수 없습니다."),
    NOT_FOUND_DEPARTMENT(NOT_FOUND, "부서를 찾을 수 없습니다."),
    NOT_FOUND_HOLIDAY(NOT_FOUND, "휴일을 찾을 수 없습니다."),
    NOT_FOUND_SCHEDULE(NOT_FOUND, "스케쥴을 찾을 수 없습니다."),
    NOT_FOUND_SCHEDULE_TYPE(NOT_FOUND, "스케쥴 타입을 찾을 수 없습니다."),

    /* 409 CONFLICT : 다른 리소스와 충돌함, 중복된 데이터 */
    DUPLICATION_MEMBER(CONFLICT, "이미 동일한 사용자가 존재합니다."),
    DUPLICATION_RESOURCE(CONFLICT, "이미 동일한 리소스가 존재합니다."),

    /* 500 INTERNAL_SERVER_ERROR : 정의되지 않은 내부 오류 */
    NOT_DEFINITION_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    DATA_OPERATION_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    DEPENDENCY_SERVER_ERROR(INTERNAL_SERVER_ERROR, "종속된 서버와의 작업 오류입니다."),
    ;

    final HttpStatus httpStatus;
    final String message;

    ErrorCode(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
