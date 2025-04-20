package com.example.marketapi.order.repository;

import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.product.domain.Reservation;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.marketapi.order.entity.QOrder.order;
import static com.example.marketapi.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManagerFactory emf;
    private final EntityManager em;

    /*@Override
    public List<Tuple> findPreservedAllByName(String name) {
        // 구매자이름이거나, 판매자이름이거나중에, 예약된것들 출력
        List<Tuple> fetch = jpaQueryFactory.select(
                        order.seller.name
                        , product.name
                        , product.price)
                .from(order)
                .join(order.product, product)
                .where(
                        (order.purchaserName.eq(name).or(order.sellerName.eq(name)))
                                .and(product.reservation.eq(Reservation.RESERVED)))
                .fetch();

        return fetch;
    }*/

    @Override
    public Optional<OrderResponseDto> findOrderDetail(Long orderId) {
        String jpqlQuery = "select new com.example.marketapi.order.dto.response.OrderResponseDto(pul.name, s.name, p.name, p.price, p.quantity, p.reservation) " +
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
