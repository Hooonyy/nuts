package com.prac.almond.app.address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prac.almond.app.address.model.Address;
import com.prac.almond.app.address.model.AddressDto;
import com.prac.almond.app.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address")
    public ModelAndView addressInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("address/addressInfo");
        return modelAndView;
    }
    @GetMapping("/address/addressInfo/{usersSeq}")
    public ModelAndView addressInfoData(@PathVariable("usersSeq") int usersSeq) {
        ModelAndView modelAndView = new ModelAndView();
        List<Address> addressInfo = addressService.findAddressInfo(usersSeq);
        modelAndView.addObject("addressInfo" , addressInfo);
        modelAndView.setViewName("address/addressInfo");
        return modelAndView;
    }
    @GetMapping("/address/insert")
    public ModelAndView addressInsert(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("address/addressInsert");
        return modelAndView;
    }
    @PostMapping("/address/save")
    @ResponseBody
    public String saveAddressData(AddressDto addressDto) {
        return addressService.saveAddressData(addressDto);
    }
}
