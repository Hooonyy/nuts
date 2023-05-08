package com.prac.almond.app.user.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private int usersSeq;

    private String usersId;

    private String password;

    private String usersName;

    private String address;

    private String age;

    private String ageEncrypt;

    private MultipartFile multipartFile;

    private String filePath;

}
