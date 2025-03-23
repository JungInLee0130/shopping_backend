package com.example.marketapi.auth.controller;

import com.example.marketapi.auth.response.LoginResponse;
import com.example.marketapi.auth.jwt.TokenProvider;
import com.example.marketapi.auth.jwt.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    TokenProvider tokenProvider;

    @MockBean
    private RestTemplate restTemplate;

    @Value("${AUTHORIZATION_URI}")
    String authorizationUri;

    @Value("${REDIRECT_URI}")
    String redirectUri;

    @Value("${CLIENT_ID}")
    String clientId;

    String url;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @DisplayName("카카오 로그인 테스트")
    public void kakaoLoginTest() throws Exception{
        url = authorizationUri +
                "?client_id=" + clientId +
                "&redirect_uri" + redirectUri +
                "&response_type=code";

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.FOUND)
                        .location(URI.create(redirectUri + "?code=user1")));
    }

    String accessToken;

    @Test
    @DisplayName("accessToken 얻기")
    void getAccessToken() throws Exception{
        LoginResponse loginResponse = new LoginResponse("1");

        String jsonRequest = objectMapper.writeValueAsString(loginResponse);

        accessToken = "1";

        mockMvc.perform(get("/api/auth/success")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(accessToken))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "name")
    @DisplayName("로그아웃 처리: accessToken 삭제")
    void logout() throws Exception{
        //given
        doNothing().when(tokenService).deleteRefreshToken(any());

        //then
        mockMvc.perform(delete("/api/auth/logout"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
