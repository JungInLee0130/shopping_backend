package com.example.marketapi.order.repository;

import com.example.marketapi.product.domain.Preserved;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.marketapi.order.domain.QOrder.order;
import static com.example.marketapi.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> findPreservedAllByName(String name) {
        // 구매자이름이거나, 판매자이름이거나중에, 예약된것들 출력
        List<Tuple> fetch = jpaQueryFactory.select(
                        order.sellerName
                        , product.name
                        , product.price)
                .from(order)
                .innerJoin(order.product, product)
                .where(
                        (order.purchaserName.eq(name).or(order.sellerName.eq(name)))
                                .and(product.preserved.eq(Preserved.PRESERVED)))
                .fetch();

        return fetch;
    }

    // getPreservedProduct : 자신이 구매, 판매 예약한것 모두
    // request : String name

}
