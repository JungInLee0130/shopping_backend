package com.example.marketapi.payment.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.payment.domain.PaymentState;
import com.example.marketapi.payment.entity.Payment;
import com.example.marketapi.payment.repository.PaymentRepository;
import com.example.marketapi.payment.request.PaymentCancelRequest;
import com.example.marketapi.payment.request.PaymentProgressRequest;
import com.example.marketapi.payment.response.PaymentCancelResponse;
import com.example.marketapi.payment.response.PaymentProgressResponse;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.entity.TransactLog;
import com.example.marketapi.transact.model.TransactState;
import com.example.marketapi.transact.repository.TransactLogRepository;
import com.example.marketapi.transact.repository.TransactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TransactLogRepository transactLogRepository;

    @Transactional
    public PaymentProgressResponse progress(PaymentProgressRequest paymentProgressRequest){
        TransactLog transactLog = transactLogRepository.findById(paymentProgressRequest.merchant_uid())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        // approve상태인 transactlog 결제진행
        validateTransactState(transactLog);

        // createPayment : 진행을 할때 만드는듯.
        Payment payment = new Payment(paymentProgressRequest.merchant_uid(),
                paymentProgressRequest.amount(), PaymentState.PROGRESS);

        paymentRepository.save(payment);

        return new PaymentProgressResponse(payment);
    }

    private void validateTransactState(TransactLog transactLog) {
        if (!transactLog.getTransactState().equals(TransactState.APPROVE)) {
            throw new CustomException(ErrorCode.NOT_FOUND, "승인된 내역이 없습니다.");
        }
    }

    @Transactional
    public PaymentCancelResponse cancel(PaymentCancelRequest paymentCancelRequest) {
        Payment payment = paymentRepository.findById(paymentCancelRequest.merchant_uid())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "HAS_NO_PAYMENTS"));

        // payment.progress -> payment.none으로
        // none으로 만들고 남겨두고 나중에 변경하는걸로할까
        TransactLog transactLog = transactLogRepository.findById(paymentCancelRequest.merchant_uid())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "HAS_NO_TRANSACT_LOGS"));

        // 가격 비교 : 일치해야함.
        Long actualAmount = Long.valueOf(transactLog.getTransact().getPrice().value());
        validateAmount(paymentCancelRequest.payAmount(), actualAmount);

        // 가격 불일치 -> 취소
        payment.updatePaymentState(PaymentState.CANCELED);

        return new PaymentCancelResponse(payment, actualAmount);
    }

    private void validateAmount(Long payAmount, Long transactAmount) {
        if (payAmount.equals(transactAmount)){
            throw new CustomException(ErrorCode.BAD_REQUEST, "SAME_AMOUNT"); // 일치함.
        }
    }
}
