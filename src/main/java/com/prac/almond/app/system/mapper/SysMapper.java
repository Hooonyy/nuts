package com.prac.almond.app.system.mapper;

import com.prac.almond.app.system.model.Sys;
import com.prac.almond.app.system.model.SysDto;
import com.prac.almond.app.system.model.SysParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMapper {
    List<Sys> findSystemPaging(SysParam sysParam);
    int countSystemList(SysParam sysParam);

    int saveSystemData(SysDto sysDto);

    Sys findSystemInfo(int sysSeq);

    int updateSystemData();

    int deleteSystemData(int sysSeq);
}
