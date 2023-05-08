package com.prac.almond.app.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserParam {

    //선택한 페이지
    private int pageNum = 1;
    //search id
    private String usersId;

    private int pageSize = 5;
}
