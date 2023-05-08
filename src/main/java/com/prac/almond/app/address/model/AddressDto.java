package com.prac.almond.app.address.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private int usersAddressSeq;
    private int usersSeq;
    private String address1;
    private String address2;
    private String nickname;
    private String mainAddress;

}
