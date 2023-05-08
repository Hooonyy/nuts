package com.prac.almond.app.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println(status);
        System.out.println(Integer.valueOf(status.toString()));
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == 500) {
                return "redirect:/main";
            }
            return "error";
        }
        return "error";
    }
}