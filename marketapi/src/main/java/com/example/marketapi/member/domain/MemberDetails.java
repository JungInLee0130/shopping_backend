package com.example.marketapi.member.domain;

import com.example.marketapi.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record MemberDetails(Member member,
                            Map<String, Object>attributes,
                            String attributeKey) implements OAuth2User, UserDetails {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(member.getRole().getKey())
        );
    }

    @Override
    public String getPassword() {
        return null; // oauth2라 없음
    }

    @Override
    public String getUsername() {
        return member.getName();
    }
    // 이름, 이메일, 프로필

    public String getEmail(){
        return member.getEmail();
    }

    public String getProfile(){
        return member.getProfile();
    }

    public Long getMemberId(){
        return member.getId();
    }

    public String getMemberUUID(){
        return member.getMemberKey();
    }
}
