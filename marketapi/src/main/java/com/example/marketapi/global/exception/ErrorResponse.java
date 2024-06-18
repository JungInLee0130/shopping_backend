package com.example.marketapi.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private int status;
    private String message;


    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e, String message) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getStatus().value())
                        .code(e.name())
                        .message(message)
                        .build());
    }
}
