package com.example.marketapi.domain.member;

public enum Role {
    User("회원");

    final private String role;

    Role(String role) {
        this.role = role;
    }

    String getRole(){
        return role;
    }
}
