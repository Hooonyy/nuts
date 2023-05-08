package com.prac.almond.app.system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDto {
    private int sysSeq;
    private String systemNm;
    private String divisionWork;
    private String institutionName;
    private String tel;
    private String email;
    private String useMetasysYn;
    private String maintenPerson;
    private String maintenTel;
    private String maintenEmail;
}
