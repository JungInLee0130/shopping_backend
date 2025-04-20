package com.example.marketapi.payment.controller;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.payment.request.PaymentCancelRequest;
import com.example.marketapi.payment.request.PaymentProgressRequest;
import com.example.marketapi.payment.response.PaymentCancelResponse;
import com.example.marketapi.payment.response.PaymentProgressResponse;
import com.example.marketapi.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    //결제진행, 가상계좌, 결제취소
    // 결제 진행 : 결제 금액과 주문금액이 일치하면 결제
    // 결제 취소 : 결제 금액과 주문금액이 불일치하면 취소
    private final PaymentService paymentService;

    @Value("${toss.api.key}")
    private String TOSS_API_KEY;

    @PostMapping
    public String paymentProduce(){
        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://pay.toss.im/api/v2/payments");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("orderNo", "1");
            jsonBody.put("amount", 10000);
            jsonBody.put("amountTaxFree", 0);
            jsonBody.put("productDesc", "테스트 결제");
            // api key
            jsonBody.put("apiKey", TOSS_API_KEY);
            //
            jsonBody.put("autoExecute", true);
            jsonBody.put("resultCallback", "https://pay.toss.im/payfront/demo/callback");
            jsonBody.put("callbackVersion", "V2");
            jsonBody.put("retUrl", "https://pay.toss.im/payfront/demo/completed?orderno=1");
            jsonBody.put("retCancelUrl", "https://pay.toss.im/payfront/demo/cancel");

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

            bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();

        } catch (Exception e) {
            responseBody.append(e);
        }
        return responseBody.toString();
    }
    // 결제 진행
    /*@PostMapping
    public ResponseEntity<PaymentProgressResponse> paymentProgress(PaymentProgressRequest paymentProgressRequest){
        PaymentProgressResponse paymentProgressResponse = paymentService.progress(paymentProgressRequest);
        return ResponseEntity.ok(paymentProgressResponse);
    }*/

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
