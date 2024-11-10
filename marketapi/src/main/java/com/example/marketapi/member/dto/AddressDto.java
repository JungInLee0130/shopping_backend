package com.example.marketapi.member.dto;

import com.example.marketapi.member.domain.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressDto (
        @NotBlank
        String roadAddress,
        @NotBlank
        String addressDetail,
        @NotBlank
        String zipCode
)
{
    public static AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .roadAddress(address.getRoadAddress())
                .addressDetail(address.getAddressDetail())
                .zipCode(address.getZipcode())
                .build();
    }

    public Address toEntity(){
        return Address.builder()
                .roadAddress(roadAddress)
                .addressDetail(addressDetail)
                .zipcode(zipCode)
                .build();
    }
}
