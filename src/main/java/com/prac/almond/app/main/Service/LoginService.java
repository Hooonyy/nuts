package com.prac.almond.app.main.Service;

import com.prac.almond.app.annotation.CheckLoginStatus;
import com.prac.almond.app.enumClass.UserLevel;
import com.prac.almond.app.main.mapper.LoginMapper;
import com.prac.almond.app.main.model.LoginUser;
import com.prac.almond.app.user.model.User;
import com.prac.almond.app.util.Aes256;
import com.prac.almond.app.util.Sha512;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//https://velog.io/@latte_h/Springboot-%EC%9B%B9%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-17-%EC%95%94%EB%B3%B5%ED%98%B8%ED%99%94
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;
    private final Sha512 sha512;
    private final Aes256 aes256;

    public User userLogin(LoginUser loginUser) throws Exception {

        if (loginUser.getUsersId() != null) {

            //pw는 암호화 한뒤 카운터
            String matchPassword = sha512.encSha256(loginUser.getPassword());
            loginUser.setPassword(matchPassword);
            //id는 mapper를 통해 카운터
            int confirmUser = loginMapper.confirmUser(loginUser);
            if (confirmUser == 1) {
                User selectUser = loginMapper.selectUser(loginUser);
                selectUser.setAge(Aes256.decrypt(selectUser.getAge()));
                return selectUser;
            } else {
                System.out.println("fail");
            }
        } else {
            System.out.println("id와 패스워드는 필수 입력값입니다. 확인해주세요");
        }
        return null;
    }

    public boolean checkLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return true;
            }
        }
        return false;

    }
}
