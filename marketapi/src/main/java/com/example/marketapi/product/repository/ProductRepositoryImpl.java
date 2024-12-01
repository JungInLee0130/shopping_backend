package com.example.marketapi.product.repository;

import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.QProduct;
import com.example.marketapi.product.response.*;
import com.example.marketapi.transact.entity.QTransact;
import com.example.marketapi.transact.entity.QTransactLog;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.example.marketapi.product.entity.QProduct.product;
import static com.example.marketapi.transact.entity.QTransact.transact;
import static com.example.marketapi.transact.entity.QTransactLog.transactLog;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ProductPreviewResponse> retrieveProductsPreview(Pageable pageable) {
        List<ProductPreviewResponse> content = jpaQueryFactory
                .select(new QProductPreviewResponse(
                        product.name,
                        product.price,
                        product.reservation
                ))
                .from(product)
                .orderBy(product.id.desc())

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())

                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<BuyProductResponse> retrievePurchaseProducts(Long buyerId, Pageable pageable) {
        List<BuyProductResponse> content = jpaQueryFactory
                .select(new QBuyProductResponse(
                        product.id,
                        product.reservation,
                        product.name,
                        transact.price,
                        transactLog.transactState
                ))
                .from(product)

                .innerJoin(transact)
                .on(transact.product.eq(product))

                .leftJoin(transactLog)
                .on(transact.buyer.id.eq(lastTransactLogId()))

                .where(transact.buyer.id.eq(buyerId))

                .orderBy(transact.id.desc())

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())

                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product)

                .innerJoin(transact)
                .on(transact.product.eq(product))

                .leftJoin(transactLog)
                .on(transactLog.id.eq(lastTransactLogId()))

                .where(transact.buyer.id.eq(buyerId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    // 가장 최근 거래기록 추출
    private static JPQLQuery<Long> lastTransactLogId(){
        return JPAExpressions
                .select(transactLog.id.max())
                .from(transact)

                .innerJoin(transactLog)
                .on(transactLog.transact.eq(transact))

                .where(transact.product.eq(product));
    }

    @Override
    public Page<ReserveProductResponse> retrieveReserveProducts(Long sellerId, Pageable pageable) {
        List<ReserveProductResponse> content = jpaQueryFactory
                .select(new QReserveProductResponse(
                        product.id,
                        product.name,
                        product.price
                ))

                .from(product)

                .where(product.reservation.eq(Reservation.RESERVED),
                        product.seller.id.eq(sellerId))

                .orderBy(product.id.desc())

                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())

                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product)

                .where(product.reservation.eq(Reservation.RESERVED));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
