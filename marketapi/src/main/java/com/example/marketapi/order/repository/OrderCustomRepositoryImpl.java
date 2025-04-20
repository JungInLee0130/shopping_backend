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

@Slf4j
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
        /*String jpqlQuery = "select new com.marketapi.order.dto.response.orderResponseDto(pu.name, s.name, p.name, p.price, p.quantity, p.reservation)" +
                " from order o" +
                " join fetch member pu" +
                " on o.purchaser := pu.member" +
                " join fetch product p" +
                " on o.product := p.product" +
                " join fetch p.seller s" +
                " on ";*/

        String jpqlQuery = "select new com.example.marketapi.order.dto.response.OrderResponseDto(pul.name, s.name, p.name, p.price, p.quantity, p.reservation) " +
                "from Order o " +
                "join o.product p " +
                "join o.purchaser pul " +
                "join p.seller s " +
                "where o.id = :orderId";

        /*String query = "SELECT SEL.MEMBER_NAME AS SELLER_NAME, PUR.MEMBER_NAME AS PURCHASER_NAME, P.PRODUCT_NAME, P.PRICE, P.QUANTITY, P.RESERVATION " +
                "FROM ORDER O " +
                "INNER JOIN PRODUCT P " +
                "ON O.PRODUCT_ID = P.PRODUCT_ID " +
                "INNER JOIN MEMBER PUR " +
                "ON O.PURCHASER_ID = PUR.MEMBER_ID " +
                "INNER JOIN MEMBER SEL " +
                "ON P.SELLER_ID = SEL.MEMBER_ID " +
                "WHERE O.ORDER_ID = ?";*/

        /*em.createNativeQuery(query, OrderResponseDto.class)
                .setParameter(1, orderId)
                .getSingleResult();*/

        TypedQuery<OrderResponseDto> typedQuery = em.createQuery(jpqlQuery, OrderResponseDto.class)
                .setParameter("orderId", orderId);

        List<OrderResponseDto> results = typedQuery.getResultList();

        log.info("반환결과 개수 : {}", results.size());

        return Optional.ofNullable(results.get(0));
    }

    // getPreservedProduct : 자신이 구매, 판매 예약한것 모두
    // request : String name
}
