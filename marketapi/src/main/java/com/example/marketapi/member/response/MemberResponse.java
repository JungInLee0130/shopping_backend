package com.example.marketapi.member.response;

import com.example.marketapi.member.entity.Address;
import com.example.marketapi.member.entity.Member;
import com.example.marketapi.member.request.AddressRequest;

import java.util.Optional;

public record MemberResponse (String name,
                              String email,
                              Optional<String> profile,
                              Optional<AddressInfo> addressInfo){

    // non-canonical constructor 오류 : 사용자 정의 생성자는 항상 canonical로 정의해야한다.
}
