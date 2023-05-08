package com.prac.almond.app.system.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SysPaging {
    private List<Sys> systemList;
    private int page = 1;
    private int listSize;
    private int pageSize = 5;
}
