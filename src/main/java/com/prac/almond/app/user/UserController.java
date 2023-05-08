package com.prac.almond.app.user;

import com.prac.almond.app.annotation.CheckLoginStatus;
import com.prac.almond.app.enumClass.UserLevel;
import com.prac.almond.app.main.model.LoginUser;
import com.prac.almond.app.user.model.User;
import com.prac.almond.app.user.model.UserDto;
import com.prac.almond.app.user.model.UserPaging;
import com.prac.almond.app.user.model.UserParam;
import com.prac.almond.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CheckLoginStatus(auth = UserLevel.USER)
    @GetMapping("/user")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userList");
        return modelAndView;
    }

    //유저 리스트
    @GetMapping("/user/userList")
    @ResponseBody
    public Object userListData(UserParam userParam) throws Exception {
        return userService.findUserPaging(userParam);
    }

    //로그인유저 정보
    @GetMapping("/user/userProfile")
    @ResponseBody
    public ModelAndView userProfile(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(session.getAttribute("user"));
        return modelAndView;
    }

    //유저 선택팝업
    @GetMapping("/user/userListPopup")
    public ModelAndView userListPopup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userListPopup");
        return modelAndView;
    }

    //유저등록
    @GetMapping("/user/register")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/registerUser");
        return modelAndView;
    }

    //유저저장
    @PostMapping("/user/save")
    @ResponseBody
    public String saveUserData(UserDto userDto) throws Exception {
        System.out.println(userDto.getMultipartFile().getOriginalFilename());
        return userService.saveUserData(userDto);
    }

    //유저 개인 정보
    @GetMapping("/user/userInfo/{usersSeq}")
    public ModelAndView userInfo(@PathVariable("usersSeq") int usersSeq) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User userInfo = userService.findUserInfo(usersSeq);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.setViewName("user/userInfo");
        return modelAndView;
    }

    //유저정보 수정
    @PutMapping("/user/update")
    @ResponseBody
    public String updateUserData(UserDto userDto) throws Exception {
        return userService.updateUserData(userDto);
    }

    //유저정보 삭제
    @DeleteMapping("/user/delete")
    @ResponseBody
    public String deleteUserData(User user) {
        return userService.deleteUserData(user.getUsersSeq());
    }


}
