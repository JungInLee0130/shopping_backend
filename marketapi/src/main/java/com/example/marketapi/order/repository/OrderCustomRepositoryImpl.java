package com.example.marketapi.order.repository;

import com.example.marketapi.order.domain.OrderStatus;
import com.example.marketapi.order.dto.response.OrderLogResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.product.domain.Reservation;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.marketapi.order.entity.QOrder.order;
import static com.example.marketapi.product.entity.QProduct.product;
import static com.example.marketapi.order.entity.QOrderLog.orderLog;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public Optional<List<OrderLogResponseDto>> findPreservedOrderLogAll(Long memberId) {
        // 자신이 예약하거나 예약당한 내역들을 출력
        // querydsl
        List<OrderLogResponseDto> results = jpaQueryFactory.select(Projections.constructor(OrderLogResponseDto.class,
                        orderLog.orderLogStatus,
                        order.purchaser.name,
                        product.seller.name,
                        order.product.name,
                        product.price,
                        product.quantity))
                .from(orderLog)
                .leftJoin(orderLog.order, order)
                .leftJoin(order.product, product)
                .where(order.purchaser.id.eq(memberId)
                        .or(product.seller.id.eq(memberId))
                        .and(orderLog.orderLogStatus.eq(OrderStatus.PRESERVED)))
                .fetch();

        return Optional.ofNullable(results);
    }

    @Override
    public Optional<List<OrderLogResponseDto>> findPreservedOrderLogAllByJPQL(Long memberId) {
        String jpql = "select new com.example.marketapi.order.dto.response.OrderLogResponseDto" +
                "(ol.orderLogStatus, pul.name, s.name, p.name, p.price, p.quantity) " +
                "from OrderLog ol " +
                "left join ol.order o " +
                "left join o.product p " +
                "left join o.purchaser pul " +
                "left join p.seller s " +
                "where pul.id = :memberId or s.id = :memberId " +
                "and ol.orderLogStatus =: orderLogStatus";

        List<OrderLogResponseDto> results = em.createQuery(jpql, OrderLogResponseDto.class)
                .setParameter("memberId", memberId)
                .setParameter("orderLogStatus", OrderStatus.PRESERVED)
                .getResultList();

        return Optional.ofNullable(results);
    }

    @Override
    public Optional<OrderResponseDto> findOrderDetail(Long orderId) {
        String jpqlQuery = "select new com.example.marketapi.order.dto.response.OrderResponseDto" +
                "(pul.name, s.name, p.name, p.price, p.quantity, p.reservation) " +
                "from Order o " +
                "join o.product p " +
                "join o.purchaser pul " +
                "join p.seller s " +
                "where o.id = :orderId";

        OrderResponseDto orderResponseDto = em.createQuery(jpqlQuery, OrderResponseDto.class)
                .setParameter("orderId", orderId)
                .getSingleResult();

        return Optional.ofNullable(orderResponseDto);
    }

    // getPreservedProduct : 자신이 구매, 판매 예약한것 모두
    // request : String name
}
