package com.example.marketapi.transact.repository;

import com.example.marketapi.product.entity.Product;
import com.example.marketapi.transact.entity.QTransact;
import com.example.marketapi.transact.entity.QTransactLog;
import com.example.marketapi.transact.model.TransactState;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static com.example.marketapi.transact.entity.QTransact.transact;
import static com.example.marketapi.transact.entity.QTransactLog.transactLog;


@RequiredArgsConstructor
public class TransactRepositoryImpl implements TransactRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<TransactState> retrieveAllTransactState(Long transactId) {
        return jpaQueryFactory
                .select(transactLog.transactState)
                .from(transact)

                .innerJoin(transactLog)
                .on(transact.eq(transactLog.transact))

                .where(transact.id.eq(transactId))

                .fetch();
    }

    @Override
    public List<TransactState> retrieveAllTransactState(Long buyerId, Long productId) {
        return jpaQueryFactory
                .select(transactLog.transactState)
                .from(transact)

                .innerJoin(transactLog)
                .on(transact.eq(transactLog.transact))

                .where(transact.buyer.id.eq(buyerId),
                        transact.product.id.eq(productId))

                .fetch();
    }

    @Override
    public TransactState retrieveLastTransactState(Long buyerId, Long productId) {
        return jpaQueryFactory
                .select(transactLog.transactState)
                .from(transact)

                .innerJoin(transactLog)
                .on(transact.eq(transactLog.transact))

                .where(transact.buyer.id.eq(buyerId),
                        transact.product.id.eq(productId),
                        transactLog.id.in(lastTransactLogId()))

                .fetchOne();
    }

    private static JPQLQuery<Long> lastTransactLogId(){
        return JPAExpressions
                .select(transactLog.id.max())
                .from(transactLog)
                .groupBy(transactLog.transact); //..?
    }

    @Override
    public Set<TransactState> retrieveDistinctProductTransactState(Product product) {
        List<TransactState> transactStates = jpaQueryFactory
                .selectDistinct(transactLog.transactState)
                .from(transact)

                .innerJoin(transactLog)
                .on(transact.eq(transactLog.transact))

                .where(transact.product.eq(product),
                        transactLog.id.in(lastTransactLogId()))

                .fetch();

        return EnumSet.copyOf(transactStates);
    }
}
