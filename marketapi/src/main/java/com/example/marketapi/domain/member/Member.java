package com.example.marketapi.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private Role role;

    @Builder
    private Member(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
