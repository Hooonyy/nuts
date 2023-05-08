package com.prac.almond.app.util;

import com.prac.almond.app.main.model.LoginUser;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class Sha512 {
    String salt = "1111111111111111";

    public String encSha256(String password) {

        String encPwd = password + salt;


        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(password.getBytes("utf8"));
            encPwd = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encPwd;

    }


}
