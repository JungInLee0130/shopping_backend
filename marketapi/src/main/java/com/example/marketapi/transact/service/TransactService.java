package com.example.marketapi.transact.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.repository.ProductRepository;
import com.example.marketapi.transact.entity.Transact;
import com.example.marketapi.transact.entity.TransactLog;
import com.example.marketapi.transact.model.TransactState;
import com.example.marketapi.transact.repository.TransactLogRepository;
import com.example.marketapi.transact.repository.TransactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactService {
    private final TransactRepository transactRepository;
    private final TransactLogRepository transactLogRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void approve(Long productId, Long buyerId, Long sellerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "제품을 찾을수없습니다."));

        if (!product.getSeller().getId().equals(sellerId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "물건의 판매자와 판매자가 일치하지않습니다.");
        }

        Transact transact = transactRepository.findByBuyerIdAndProductId(buyerId, productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "거래중인내역을 찾을수없습니다."));

        List<TransactState> transactStates = transactRepository.retrieveAllTransactState(transact.getId());

    }

    @Transactional
    public void confirm(Long productId, Long buyerId){
        Product product = productRepository.findProductWithUpdateLockById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        TransactState lastTransactState = transactRepository.
                retrieveLastTransactState(buyerId, productId);

        if (lastTransactState != TransactState.APPROVE) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "CAN_NOT_CONFIRM");
        }

        Transact transact = transactRepository.findByBuyerIdAndProductId(buyerId, productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        transactLogRepository.save(new TransactLog(transact, TransactState.CONFIRM));

        isAllConfirm(product);
    }

    private void isAllConfirm(Product product) {
        Set<TransactState> transactStates = transactRepository.retrieveDistinctProductTransactState(product);

        if (transactStates.size() == 1 && transactStates.contains(TransactState.CONFIRM)) {
            product.complete();
        }
    }
}
