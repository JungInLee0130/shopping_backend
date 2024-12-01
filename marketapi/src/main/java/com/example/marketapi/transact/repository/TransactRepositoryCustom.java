package com.example.marketapi.transact.repository;

import com.example.marketapi.product.entity.Product;
import com.example.marketapi.transact.model.TransactState;

import java.util.List;
import java.util.Set;

public interface TransactRepositoryCustom {

    List<TransactState> retrieveAllTransactState(Long transactId);

    List<TransactState> retrieveAllTransactState(Long buyerId, Long productId);

    TransactState retrieveLastTransactState(Long buyerId, Long productId);

    Set<TransactState> retrieveDistinctProductTransactState(Product product);
}
