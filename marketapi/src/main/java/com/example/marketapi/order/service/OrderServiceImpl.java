package com.example.marketapi.order.service;

import com.example.marketapi.order.domain.Order;
import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.repository.OrderRepository;
import com.example.marketapi.order.dto.request.OrderApprovalRequestDto;
import com.example.marketapi.product.domain.Preserved;
import com.example.marketapi.product.domain.Product;
import com.example.marketapi.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    public OrderResponseDto orderDetails(String name) {
        Order order = orderRepository.findBySellerNameOrPurchaserName(name)
                .orElseThrow(() -> new NoSuchElementException());
        return OrderResponseDto.of(order);
    }

    @Override
    public void approveSell(OrderApprovalRequestDto orderApprovalRequestDto) {
        String productName = orderApprovalRequestDto.getProductName();
        String sellerName = orderApprovalRequestDto.getSellerName();
        String purchaserName = orderApprovalRequestDto.getPurchaserName();


        Order order = orderRepository.findByNameAndSellerNameAndPurchaserName(productName, purchaserName, sellerName)
                .orElseThrow(() -> new NoSuchElementException());

        Long productId = order.getProductId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException());

        // 구매 승인
        product.updatePreserved(Preserved.FINISH);
    }
}
