package com.example.marketapi.member.request;

import jakarta.validation.constraints.NotBlank;

public record MemberEditRequest (@NotBlank String name,
                                 AddressRequest addressRequest){
}
