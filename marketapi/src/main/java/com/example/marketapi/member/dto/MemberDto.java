package com.example.marketapi.member.dto;

import com.example.marketapi.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String email;
    private String profile;
    private AddressDto address;

    public static MemberDto fromEntity(Member member) {
        MemberDto memberDto = MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .profile(member.getProfile())
                .build();

        if (member.getAddress() != null) {
            memberDto.address = AddressDto.fromEntity(member.getAddress());
        }

        return memberDto;
    }
}
