package com.example.marketapi.auth.dto;

import com.example.marketapi.global.exception.CustomException;
import com.example.marketapi.global.exception.ErrorCode;
import com.example.marketapi.member.domain.Member;
import com.example.marketapi.member.domain.Role;
import lombok.Builder;
import java.util.Map;

@Builder
public record OAuth2UserInfo (String name, String email, String profile){
    public static OAuth2UserInfo of (String registrationId,
                                     Map<String, Object> attributes){
        return switch(registrationId){
            case "google" -> ofGoogle(attributes);
            case "kakao" -> ofKakao(attributes);
            default -> throw new CustomException(ErrorCode.BAD_REQUEST, "ILLEGAL_REGISTRATION_ID");
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes){
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes){
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");

        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .profile((String) profile.get("profile_image_url"))
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .profile(profile)
                .role(Role.USER)
                .build();
    }
}
