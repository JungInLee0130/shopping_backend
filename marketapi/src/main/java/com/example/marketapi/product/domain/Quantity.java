package com.example.marketapi.product.domain;


import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;

public record Quantity (int value){
    public Quantity { // quantity 생성시 validation 체크
        validValue(value);
    }

    // 수량감소(객체 지향적으로)
    public static Quantity minus(Quantity quantity) {
        return new Quantity(quantity.value - 1);
    }

    private void validValue(int value) {
        if (value < 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "수량은 0이하를 입력할수없습니다.");
        }
    }
}
