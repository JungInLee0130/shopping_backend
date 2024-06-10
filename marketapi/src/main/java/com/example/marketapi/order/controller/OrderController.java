package com.example.marketapi.order.controller;

import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderPurchasedResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.service.OrderService;
import com.example.marketapi.order.dto.request.OrderApprovalRequestDto;
import com.example.marketapi.order.dto.response.OrderPreservedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 거래시작 : 구매자
    @GetMapping("/buy")
    public ResponseEntity<Void> orderProduct(OrderRequestDto orderRequestDto) {
        // 구매자이름 + 제품 정보 + 판매자이름을 저장
        orderService.orderProduct(orderRequestDto);

        return ResponseEntity.ok().build();
    }

    // 거래내역 확인 : 판매자, 구매자
    @GetMapping("/detail")
    public OrderResponseDto orderDetails(Long productId) {
        return orderService.orderDetails(productId);
    }

    // 구매한 용품(구매자)
    @GetMapping("/purchased")
    public ResponseEntity<List<OrderPurchasedResponseDto>> getPurchasedProducts(String purchaserName) {
        return ResponseEntity.ok(orderService.getPurchasedProducts(purchaserName));
    }

    // 예약중인 용품 확인가능(구매자, 판매자 모두)
    @GetMapping("/preserved")
    public ResponseEntity<List<OrderPreservedResponseDto>> getPreservedProducts(String name) {
        return ResponseEntity.ok(orderService.getPreservedProducts(name));
    }


    // 판매승인 : 판매자
    @PutMapping("/approve")
    // parameter : 판매자 이름, 제품이름, 구매자이름
    public ResponseEntity<Void> approveSell(Long id) {
        orderService.approveSell(id);
        return ResponseEntity.ok().build();
    }
}
