package com.example.marketapi.product.domain;


import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;

public record Price(int value) {

    public Price{
        validValue(value);
    }

    private void validValue(int value){
        if (value <= 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "금액은 0이하일수없습니다.");
        }
    }
}
