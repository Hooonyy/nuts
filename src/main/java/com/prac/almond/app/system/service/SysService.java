package com.prac.almond.app.system.service;

import com.prac.almond.app.product.model.Product;
import com.prac.almond.app.system.mapper.SysMapper;
import com.prac.almond.app.system.model.Sys;
import com.prac.almond.app.system.model.SysDto;
import com.prac.almond.app.system.model.SysPaging;
import com.prac.almond.app.system.model.SysParam;
import com.prac.almond.app.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysService {
    private final SysMapper sysMapper;

    public SysPaging findSystemPaging(SysParam sysParam){
        List<Sys> list = sysMapper.findSystemPaging(sysParam);

        SysPaging sysPaging = new SysPaging();
        sysPaging.setSystemList(list);

        int countSystemList = sysMapper.countSystemList(sysParam);
        sysPaging.setListSize(countSystemList);
        sysPaging.setPage(sysParam.getPageNum());
        return sysPaging;
    }

    public String saveSystemData(SysDto systemDto) {
        int result = sysMapper.saveSystemData(systemDto);
        if(result > 0){
            return "success";
        }
        return "fail";
    }

    public Sys findSystemInfo(int sysSeq) {
        Sys sysInfoData = sysMapper.findSystemInfo(sysSeq);
        return sysInfoData;
    }

    public String updateSystemData(SysDto sysDto) {
        Sys sys = new Sys();

        sys.setSysSeq(sysDto.getSysSeq());
        sys.setSystemNm(sysDto.getSystemNm());
        sys.setEmail(sysDto.getEmail());
        sys.setDivisionWork(sysDto.getDivisionWork());
        sys.setTel(sysDto.getTel());
        sys.setInstitutionName(sysDto.getInstitutionName());
        sys.setMaintenEmail(sysDto.getMaintenEmail());
        sys.setMaintenEmail(sysDto.getMaintenEmail());
        sys.setMaintenPerson(sysDto.getMaintenPerson());

        int updateProductData = sysMapper.updateSystemData();
        if (updateProductData == 1) {
            return "success";
        }
        return "fail";
    }

    public String deleteSystemData(int sysSeq) {
        int deleteSystem = sysMapper.deleteSystemData(sysSeq);
        if (deleteSystem == 1) {
            return "success";
        }
        return "fail";
    }
}
