package com.CafeHub.Manage.Admin.controller;


import com.CafeHub.Manage.Admin.AdminConfig;
import com.CafeHub.Manage.Admin.exception.SecretCodeInvaildException;
import com.CafeHub.Manage.Admin.request.SignupRequest;
import com.CafeHub.Manage.Admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    private final AdminConfig adminConfig;



    @GetMapping("/signup")
    public String signupPage() {

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute SignupRequest request,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){

            for (FieldError error : bindingResult.getFieldErrors()){
                model.addAttribute(error.getField() + "Error" , error.getDefaultMessage());
            }

            return "signup";
        }

        if(!request.getCode().equals(adminConfig.getSecretCode())) throw new SecretCodeInvaildException();


        adminService.signup(request);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false)String error, Model model){

        if(error!=null) model.addAttribute("error", "아이디나 비밀번호를 다시 확인하세요.");

        return "login";
    }

}
