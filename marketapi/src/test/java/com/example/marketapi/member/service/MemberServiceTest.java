package com.example.marketapi.member.service;

import com.example.marketapi.member.domain.MemberDetails;
import com.example.marketapi.member.domain.Role;
import com.example.marketapi.member.entity.Address;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.repository.MemberRepository;
import com.example.marketapi.member.request.AddressRequest;
import com.example.marketapi.member.request.MemberEditRequest;
import com.example.marketapi.member.response.AddressInfo;
import com.example.marketapi.member.response.MemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @InjectMocks
    MemberDetails memberDetails;

    @Mock
    MemberRepository memberRepository;

    Member member;
    Long id;

    @BeforeEach
    void setup(){
        member = new Member("1L", "dl@naver.com", "김하성", Role.USER);
        id = 1L;
    }

    @Test
    @DisplayName("멤버 정보 불러오기")
    void memberInfoTest(){
        // given

        given(memberRepository.findById(id)).willReturn(Optional.ofNullable(member));

        // when
        MemberResponse expected = new MemberResponse(member.getName(),
                member.getEmail(),
                getProfile(member.getProfile()),
                getAddress(member.getAddress()));
        MemberResponse actual = memberService.memberInfo(id);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private Optional<String> getProfile(String profile){
        if (Objects.isNull(profile)) {
            return Optional.empty();
        }
        return Optional.of(profile);
    }

    private Optional<AddressInfo> getAddress(Address address) {
        if (Objects.isNull(address)) {
            return Optional.empty();
        }
        return Optional.of(new AddressInfo(address));
    }
    
    @Test
    @DisplayName("주소, 이름 변경")
    void memberEdit(){
        //given
        given(memberRepository.findById(id)).willReturn(Optional.ofNullable(member));
        //when
        MemberEditRequest memberEditRequest = new MemberEditRequest("김하성2",
                new AddressRequest("서울 강남구 테헤란로 212",
                        "멀티캠퍼스 역삼",
                        "06220"));
        MemberResponse memberResponse = memberService.memberEdit(memberEditRequest, id);

        String actualRoadAddress = memberRepository.findById(id).get().getAddress().getRoadAddress();
        //then
        assertThat(actualRoadAddress).isEqualTo("서울 강남구 테헤란로 212");
    }
}
