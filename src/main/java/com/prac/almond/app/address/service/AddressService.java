package com.prac.almond.app.address.service;

import com.prac.almond.app.address.mapper.AddressMapper;
import com.prac.almond.app.address.model.Address;
import com.prac.almond.app.address.model.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressMapper addressMapper;

    public List<Address> findAddressInfo(Integer usersSeq) {
        List<Address> addressInfo = addressMapper.findAddressInfo(usersSeq);
        return addressInfo;
    }

    public String saveAddressData(AddressDto addressDto) {
        int addrCount = addressMapper.countMainAddr(addressDto.getUsersSeq());
        if (addrCount == 0) {
            addressDto.setMainAddress("Y");

        } else {
            if (addressDto.getMainAddress().equals("Y")) {
                int updateAddr = addressMapper.updateMainAddr(addressDto.getUsersSeq());
            }
        }
        int addressSaveData = addressMapper.saveAddressData(addressDto);
        if (addressSaveData == 1) {
            return "success";
        }
        return "fail";

    }
}
