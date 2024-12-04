package com.example.marketapi.member.request;

import com.example.marketapi.member.entity.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressRequest(@NotBlank String roadAddress,
                             @NotBlank String addressDetail,
                             @NotBlank String zipCode)
{
    public AddressRequest(Address address) {
        this(address.getRoadAddress(), address.getAddressDetail(), address.getZipcode());
    }
}
