package com.example.marketapi.product.controller;

import com.example.marketapi.auth.jwt.TokenProvider;
import com.example.marketapi.product.domain.Price;
import com.example.marketapi.product.domain.Quantity;
import com.example.marketapi.product.dto.request.ProductRequestDto;
import com.example.marketapi.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    TokenProvider tokenProvider;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    static final String uri = "/api/v1/product";

    String name;
    Price price;
    Quantity quantity;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        name= "건담";
        price = new Price(5000);
        quantity = new Quantity(1);
    }

    @Test
    @DisplayName("제품 등록 : 회원만")
    void addProductControllerTest() throws Exception{
        ProductRequestDto requestDto = new ProductRequestDto(name, price.value(), quantity.value());
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        ResultActions perform = mockMvc.perform(post(uri + "/add")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON));
                //.headers(authorizationHeader())); // spring rest docs (swagger 대신 사용)

        perform.andExpect(status().isCreated());
        perform.andDo(print());
    }
}