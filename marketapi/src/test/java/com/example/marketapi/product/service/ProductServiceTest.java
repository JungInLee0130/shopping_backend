package com.example.marketapi.product.service;

import com.example.marketapi.member.domain.Address;
import com.example.marketapi.member.domain.Member;
import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.member.domain.Role;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.member.service.MemberService;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.domain.Reservation;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.entity.Product;
import com.example.marketapi.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private MemberService memberService;

    @InjectMocks
    private MemberDetails memberDetails;

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MemberRepository memberRepository;

    Member member;
    Product product;
    String name;
    Price price;
    Quantity quantity;
    Long sellerId;

    @BeforeEach
    void setUp(){
        member = new Member(1L, "dl@naver.com", "김하성", "ㄹㄷㅈ", Role.USER);

        name= "건담";
        price = new Price(5000);
        quantity = new Quantity(1);
        sellerId = 1L;

        product = new Product(1L, member, name, price, quantity);
    }

    @Test
    @DisplayName("제품 등록: 회원만")
    void addProductTest(){
        //given : A일때 B가 나오는가?
        given(memberRepository.getReferenceById(sellerId)).willReturn(member);
        given(productRepository.save(any(Product.class))).willReturn(product);

        // when
        Long actual = productService.addProduct(name,price,quantity,sellerId);
        // productservice.addProduct에 1L을 넣어줘야함.
        // test code에서는 generatedValue가 실행이 안됨.
        // 다른 변수들은 적용되고 저장은됨.

        // then
        assertThat(actual).isEqualTo(product.getId());
    }
}
