package com.prac.almond.app.main;

import com.prac.almond.app.main.Service.LoginService;
import com.prac.almond.app.main.model.LoginUser;
import com.prac.almond.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final LoginService loginService;

    @GetMapping("/main")
    public ModelAndView Main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/login");
        return modelAndView;
    }

    @PostMapping("/main/login")
    @ResponseBody
    public String signUp(HttpServletRequest request, LoginUser loginUser) throws Exception {

        //useSession();
        User login = loginService.userLogin(loginUser);
        System.out.println(loginUser.getUsersId());
        System.out.println(loginUser.getPassword());
        if (login != null){
            this.useSession(request, login);
            //
            return "success";
        }
        return "fail";
    }
    @GetMapping("/main/img")
    public ModelAndView imgData(){

        return new ModelAndView();
    }

    public void useSession(HttpServletRequest request, User login) {

        HttpSession session = request.getSession();

        //세션값 설정
        session.setAttribute("user", login);
        //세션 시간 설정
        session.setMaxInactiveInterval(600);

    }
}
