package com.prac.almond.app.user.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPaging {
    private List<User> userList;
    //시작페이지
    private int page = 1;
    //전체 페이지갯수 
    private int listSize;
    //자를페이지수 offset 5씩증가 하게
    private int pageSize = 5;

}
