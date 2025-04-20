package com.example.marketapi.order.dto.response;

import com.example.marketapi.order.entity.Order;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.domain.Reservation;

public record OrderResponseDto(String purchaserName,
                               String sellerName,
                               String productName,
                               Price price,
                               Quantity quantity,
                               Reservation reservation) {
    public OrderResponseDto (Order order){
        this(order.getPurchaser().getName(),
                order.getProduct().getSeller().getName(),
                order.getProduct().getName(),
                order.getProduct().getPrice(),
                order.getProduct().getQuantity(),
                order.getProduct().getReservation());
    }
}