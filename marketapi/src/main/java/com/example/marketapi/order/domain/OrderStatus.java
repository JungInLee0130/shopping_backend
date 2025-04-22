package com.example.marketapi.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    // 거래 진행중, 예약중, 거래 완료, 거래 취소
    PROGRESS, PRESERVED, FINISHED, CANCELED
}
