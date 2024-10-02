package com.CafeHub.Manage.handler;


import com.CafeHub.Manage.Admin.exception.AdminUsernameDuplicateException;
import com.CafeHub.Manage.Admin.exception.SecretCodeInvaildException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class SignUpFailureHandler {

    @ExceptionHandler(SecretCodeInvaildException.class)
    public ModelAndView SecretCodeInvalidExceptionHandle(){

        ModelAndView mav = new ModelAndView();
        mav.addObject("codeInvalid","인증코드가 일치하지 않습니다.");
        mav.setViewName("signup");

        return mav;
    }


    @ExceptionHandler(AdminUsernameDuplicateException.class)
    public ModelAndView AdminUsernameDuplicateExceptionHandle(){

        ModelAndView mav = new ModelAndView();
        mav.addObject("usernameDuplicate", "동일한 아이디가 이미 존재합니다.");
        mav.setViewName("signup");

        return mav;
    }

}
