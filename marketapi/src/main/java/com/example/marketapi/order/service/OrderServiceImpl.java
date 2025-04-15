package com.example.marketapi.order.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.order.entity.Order;
import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderPreservedResponseDto;
import com.example.marketapi.order.dto.response.OrderPurchasedResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.repository.OrderRepository;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.repository.ProductRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public void orderProduct(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .productId(orderRequestDto.getProductId())
                .purchaserName(orderRequestDto.getPurchaserName())
                .sellerName(orderRequestDto.getSellerName())
                .build();

        orderRepository.save(order);
    }

    @Override
    public OrderResponseDto orderDetails(Long productId) {
        Order order = orderRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "거래 내역이 없습니다."));
        return OrderResponseDto.of(order);
    }


    @Override
    public List<OrderPurchasedResponseDto> getPurchasedProducts(String purchaserName) {
        // 구매자의 구매한 주문 불러오기
        List<Order> orders = orderRepository.findAllByPurchaserName(purchaserName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "구매한 주문 내역이 없습니다."));

        List<OrderPurchasedResponseDto> purchasedList = new ArrayList<>();

        for (Order order:orders) {
            Product product = productRepository.findById(order.getProductId())
                    .filter(product1 -> product1.getReservation().equals(Reservation.FINISH))
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "주문내역에 해당하는 제품이 없습니다."));

            purchasedList.add(OrderPurchasedResponseDto.of(product));
        }

        return purchasedList;
    }

    @Override
    public List<OrderPreservedResponseDto> getPreservedProducts(String name) {
        // 자신이 구매중이거나, 자신이 판매중인것 모두 출력하면된다.
        List<Tuple> preservedList = orderRepository.findPreservedAllByName(name);

        return OrderPreservedResponseDto.of(preservedList);
    }


    @Override
    public void approveSell(Long id) {
        // 현재 판매 승인할 주문을 찾습니다.
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "승인이 필요한 주문 내역이 없습니다."));

        // 올려논 상품을 불러옵니다.
        Product product = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "주문내역에 해당하는 제품이 없습니다."));

        // 올려논 상품의 판매상태를 완료시킵니다.
        product.updatePreserved(Reservation.FINISH);
    }
}
