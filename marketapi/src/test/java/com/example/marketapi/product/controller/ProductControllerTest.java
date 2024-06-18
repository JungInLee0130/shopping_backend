package com.example.marketapi.product.controller;

import com.example.marketapi.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("제품 등록 : 회원만")
    void addProductTest() throws Exception{

    }
}