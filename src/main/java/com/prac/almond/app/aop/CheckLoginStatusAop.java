package com.prac.almond.app.aop;

import com.prac.almond.app.annotation.CheckLoginStatus;
import com.prac.almond.app.enumClass.UserLevel;
import com.prac.almond.app.main.Service.LoginService;
import com.prac.almond.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckLoginStatusAop {
    private final LoginService loginService;

    @Before(value = "@annotation(checkLoginStatus)")
    public void checkStatus(CheckLoginStatus checkLoginStatus){
        UserLevel auth = checkLoginStatus.auth();

        switch(auth) {
            case USER:
                allUserLoginStatus();
                break;

            case ADMIN:
                break;

            default:
                break;
        }
    }

    @AfterReturning(pointcut = ("execution(* com.prac.almond.app.user.UserController.*(..)) OR execution(* com.prac.almond.app.system.SysController.*(..)" ),
            returning = "modelAndView")
    public void afterReturning(ModelAndView modelAndView){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("user", user);
        System.out.println(user.getUsersId());
    }
    public void allUserLoginStatus(){
        boolean isLoginUser = loginService.checkLogin();

        if(!isLoginUser){
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "user is not authorized") {
            };
        }
    }
}
