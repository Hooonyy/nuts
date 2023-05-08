package com.prac.almond.app.main.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class LoginUser {
    private int usersSeq;
    private String usersId;
    private String password;
    private String age;
}
