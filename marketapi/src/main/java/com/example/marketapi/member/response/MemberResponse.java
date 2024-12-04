package com.example.marketapi.member.response;

import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.request.AddressRequest;

public record MemberResponse (String name,
                              String email,
                              String profile,
                              AddressRequest addressRequest){ // addressRequest -> Nullable

    // non-canonical constructor 오류 : 사용자 정의 생성자는 항상 canonical로 정의해야한다.
    public MemberResponse(Member member) {
        this(member.getName(), member.getEmail(), member.getProfile(),
                new AddressRequest(member.getAddress()));
    } // 여기서 profile은 nullable임.
}
