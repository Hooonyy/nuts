package com.prac.almond.app.user.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Alias("user")
public class User {

    private int usersSeq;

    private String usersId;

    private String password;

    private String usersName;

    private String address;

    private String age;

    private String filePath;

    private String fileUploadYn;
}
