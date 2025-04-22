package com.example.marketapi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다. 로그인하세요."),
    BAD_REQUEST(HttpStatus.BAD_GATEWAY, "잘못된 요청입니다."),
    ILLEGAL_REGISTRATION_ID(HttpStatus.BAD_GATEWAY, "ILLEGAL_REGISTRATION_ID"),

    // 404 NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 데이터를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지않은 제품입니다."),
    PURCHASER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지않은 구매자입니다."),
    SELLER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지않은 판매자입니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지않은 주문입니다."),
    ORDERLOG_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지않은 주문기록입니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
