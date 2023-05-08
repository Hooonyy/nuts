package com.prac.almond.app.main.mapper;

import com.prac.almond.app.main.model.LoginUser;
import com.prac.almond.app.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {


    int confirmUser(LoginUser loginUser);

    User selectUser(LoginUser loginUser);

    int matchUser(String usersId);
}
