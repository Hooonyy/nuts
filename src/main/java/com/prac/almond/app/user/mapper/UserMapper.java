package com.prac.almond.app.user.mapper;

import com.prac.almond.app.user.model.User;
import com.prac.almond.app.user.model.UserDto;
import com.prac.almond.app.user.model.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findUserList(UserParam userParam);

    int saveUserData(UserDto userDto);

    int checkUserId(String usersId);

    int countUserList(UserParam userParam);

    User findUserInfo(Integer usersSeq);

    int updateUserData(User user);

    int deleteUserData(int usersSeq);
}
