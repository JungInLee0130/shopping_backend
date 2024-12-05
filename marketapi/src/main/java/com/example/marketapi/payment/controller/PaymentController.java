package com.example.marketapi.payment.controller;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.payment.request.PaymentCancelRequest;
import com.example.marketapi.payment.request.PaymentProgressRequest;
import com.example.marketapi.payment.response.PaymentCancelResponse;
import com.example.marketapi.payment.response.PaymentProgressResponse;
import com.example.marketapi.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    //결제진행, 가상계좌, 결제취소
    // 결제 진행 : 결제 금액과 주문금액이 일치하면 결제
    // 결제 취소 : 결제 금액과 주문금액이 불일치하면 취소
    private final PaymentService paymentService;

    // 결제 진행
    @PostMapping
    public ResponseEntity<PaymentProgressResponse> paymentProgress(PaymentProgressRequest paymentProgressRequest){
        PaymentProgressResponse paymentProgressResponse = paymentService.progress(paymentProgressRequest);
        return ResponseEntity.ok(paymentProgressResponse);
    }

    // 가상계좌
    /*@PostMapping("/account")
    public ResponseEntity<PaymentAccountResponse> vitualAccount(){
        // 결제하려는 상품과 거래 내역들이 APPROVE되었는지 모두 확인
        // 결제가 진행중인지 확인
        //
    }*/

    // 결제취소
    @PatchMapping
    public ResponseEntity<PaymentCancelResponse> paymentCancel(PaymentCancelRequest paymentCancelRequest) {
        PaymentCancelResponse paymentCancelResponse = paymentService.cancel(paymentCancelRequest);
        return ResponseEntity.ok(paymentCancelResponse);
    }
}
