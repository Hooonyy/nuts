package com.prac.almond.app.address.mapper;

import com.prac.almond.app.address.model.Address;
import com.prac.almond.app.address.model.AddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> findAddressInfo(Integer usersSeq);

    int saveAddressData(AddressDto addressDto);


    int countMainAddr(int usersSeq);


    int updateMainAddr(int usersSeq);
}
