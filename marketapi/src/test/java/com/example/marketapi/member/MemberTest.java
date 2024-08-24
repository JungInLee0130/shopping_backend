package com.example.marketapi.member;

import com.example.marketapi.member.domain.Member;
import com.example.marketapi.member.domain.Role;
import com.example.marketapi.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void BaseEntity_테스트(){
        LocalDateTime nowTime = LocalDateTime.now();

        Member member = Member.builder()
                .name("김하성")
                .role(Role.GUEST)
                .build();

        memberRepository.save(member);

        Assertions.assertThat(member.getCreatedAt()).isAfter(nowTime);
    }
}
