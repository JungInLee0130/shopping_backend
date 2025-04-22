package com.example.marketapi.order.repository;

import com.example.marketapi.order.domain.OrderStatus;
import com.example.marketapi.order.dto.response.OrderLogResponseDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderLogCustomRepositoryImpl implements OrderLogCustomRepository{
    private final EntityManager em;
    @Override
    public Optional<List<OrderLogResponseDto>> findAllByPurchaser(Long memberId) {
        String jpql = "select new com.example.marketapi.order.dto.response.OrderLogResponseDto" +
                "(ol.orderLogStatus, pur.name, s.name, p.name, p.price, p.quantity) " +
                "from OrderLog ol " +
                "left outer join ol.order o " +
                "left outer join o.product p " +
                "left outer join o.purchaser pur " +
                "left outer join p.seller s " +
                "where pur.id = :memberId and ol.orderLogStatus = :orderLogStatus";

        List<OrderLogResponseDto> resultList = em.createQuery(jpql, OrderLogResponseDto.class)
                .setParameter("memberId", memberId)
                .setParameter("orderLogStatus", OrderStatus.FINISHED)
                .getResultList();

        return Optional.ofNullable(resultList);
    }
}
