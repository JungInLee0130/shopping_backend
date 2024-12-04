package com.example.marketapi.member.entity;

import com.example.marketapi.member.request.AddressRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(name = "road_address")
    private String roadAddress;
    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "zip_code", length = 10)
    private String zipcode;

    @Builder
    public Address(String roadAddress, String addressDetail, String zipcode) {
        this.roadAddress = roadAddress;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    public Address(AddressRequest addressRequest) {
        this.roadAddress = addressRequest.roadAddress();
        this.addressDetail = addressRequest.addressDetail();
        this.zipcode = addressRequest.zipCode();
    }
}
