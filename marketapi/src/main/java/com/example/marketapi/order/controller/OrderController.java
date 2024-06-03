package com.example.marketapi.order.controller;

import com.example.marketapi.order.dto.OrderRequestDto;
import com.example.marketapi.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 거래시작 : 구매자
    @GetMapping("/order")
    public ResponseEntity<Void> orderProduct(OrderRequestDto orderRequestDto){
        // 구매자이름 + 제품 정보 + 판매자이름을 저장
        orderService.orderProduct(orderRequestDto);

        return ResponseEntity.ok().build();
    }

    // 거래내역 확인 : 판매자, 구매자

}
