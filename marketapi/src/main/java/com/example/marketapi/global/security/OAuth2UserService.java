package com.example.marketapi.global.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("Name: " + clientName);

        // db 저장로직
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        paramMap.forEach((k,v) ->{
            log.info("----------------------------");
            log.info(k + ":" + v);
        });

        String nickname = null;

        switch(clientName){
            case "kakao" :
                nickname = getNickname(paramMap);
                break;
        }

        log.info("------------------------");
        log.info(nickname);
        log.info("-------------------------");


        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);

    }

    private String getNickname(Map<String, Object> paramMap) {
        log.info("KAKAO---------------");

        Object value = paramMap.get("properties");

        Map propertiesMap = (LinkedHashMap) value;

        String nickname = (String) propertiesMap.get("nickname");

        return nickname;
    }
}
