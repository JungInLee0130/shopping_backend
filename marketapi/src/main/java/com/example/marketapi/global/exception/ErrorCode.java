package com.example.marketapi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다. 로그인하세요."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 데이터를 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_GATEWAY, "잘못된 요청입니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
