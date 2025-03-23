package com.example.marketapi.member.request;

import com.example.marketapi.member.entity.Address;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public record AddressRequest(@NotBlank String roadAddress,
                             @NotBlank String addressDetail,
                             @NotBlank String zipCode)
{
}
