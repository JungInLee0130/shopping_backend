package com.example.marketapi.product.domain;

import lombok.Getter;

@Getter
public enum Reservation {
    // 판매중, 예약중, 완료
    SALE, RESERVED, FINISH;
}
