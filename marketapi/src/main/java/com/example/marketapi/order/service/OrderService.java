package com.example.marketapi.order.service;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.order.dto.request.OrderRequestDto;
import com.example.marketapi.order.dto.response.OrderLogResponseDto;
import com.example.marketapi.order.dto.response.OrderResponseDto;
import com.example.marketapi.order.entity.Order;
import com.example.marketapi.order.repository.OrderLogRepository;
import com.example.marketapi.order.repository.OrderRepository;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService{

    private final OrderRepository orderRepository;
    private final OrderLogRepository orderLogRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public void orderProduct(OrderRequestDto orderRequestDto) {
        Product product = productRepository.findById(orderRequestDto.productId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        Member purchaser = memberRepository.findById(orderRequestDto.purchaserId())
                .orElseThrow(() -> new CustomException(ErrorCode.PURCHASER_NOT_FOUND));

        Order order = Order.builder()
                .product(product)
                .purchaser(purchaser)
                .build();

        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto orderDetails(Long orderId) {
        OrderResponseDto orderResponseDto = orderRepository.findOrderDetail(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "없는 주문입니다."));

        return orderResponseDto;
    }


    @Transactional(readOnly = true)
    public List<OrderLogResponseDto> getFinishedOrders(Long memberId) {
        // 구매자의 구매한 주문 불러오기
        List<OrderLogResponseDto> orderLogResponseDtoList = orderLogRepository.findAllByPurchaser(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.ORDERLOG_NOT_FOUND));

        /*for (Order order:orders) {
            Product product = productRepository.findById(order.getProductId())
                    .filter(product1 -> product1.getReservation().equals(Reservation.FINISH))
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "주문내역에 해당하는 제품이 없습니다."));

            purchasedList.add(OrderFinishedResponseDto.of(product));
        }*/

        return orderLogResponseDtoList;
    }

    @Transactional(readOnly = true)
    public List<OrderLogResponseDto> getPreservedProducts(Long memberId) {
        // 자신이 구매중이거나, 자신이 판매중인것 모두 출력하면된다. : querydsl
        List<OrderLogResponseDto> preservedProducts = orderRepository.findPreservedOrderLogAll(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.ORDERLOG_NOT_FOUND));

        log.info("preserved one : {}", preservedProducts.get(0));

        return preservedProducts;
    }


    /*@Transactional
    public void approveSell(Long id) {
        // 현재 판매 승인할 주문을 찾습니다.
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "승인이 필요한 주문 내역이 없습니다."));

        // 올려논 상품을 불러옵니다.
        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "주문내역에 해당하는 제품이 없습니다."));

        // 올려논 상품의 판매상태를 완료시킵니다.
        product.updatePreserved(Reservation.FINISH);
    }*/
}
