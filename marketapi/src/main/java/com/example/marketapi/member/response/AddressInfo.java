package com.example.marketapi.member.response;

import com.example.marketapi.member.entity.Address;

public record AddressInfo (String roadAddress,
                           String addressDetail,
                           String zipCode){
    public AddressInfo(Address address){
        this(address.getRoadAddress(),
                address.getAddressDetail(),
                address.getZipcode());
    }
}
