package com.example.marketapi.member.entity;

import com.example.marketapi.member.domain.Role;
import com.example.marketapi.member.request.MemberEditRequest;
import com.example.marketapi.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MEMBERS")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "member_key", nullable = false, unique = true)
    private String memberKey;

    @Embedded // @Embeddable 클래스를 가져옴. 이 내부에서 재정의를 안함. // 새로운 테이블로 재생성 x
    private Address address;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String name, String profile, String memberKey, Role role) {
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.memberKey = memberKey;
        this.role = role;
    }

    public Member(Long id, String email, String name, String profile, Role role, Address address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.role = role;

        if (address != null) {
            addAddress(address);
        }
    }

    public void addAddress(Address address) {
        this.address = address;
    }

    public void updateMember(MemberEditRequest memberEditRequest) {
        this.name = memberEditRequest.name();

        if (memberEditRequest.addressRequest() != null) {
            this.address = new Address(memberEditRequest.addressRequest());
        }
    }

    public void setId(Long id){
        this.id = id;
    }
}
