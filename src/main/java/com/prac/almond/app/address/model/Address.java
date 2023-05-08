package com.prac.almond.app.address.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("address")
public class Address {

    //users_address
    private int usersAddressSeq;
    private int usersSeq;
    private String address1;
    private String address2;
    private String nickname;

    //users
    private String usersName;
    private String usersId;

}
