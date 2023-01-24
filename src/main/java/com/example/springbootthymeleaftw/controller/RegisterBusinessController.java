package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.UserService;
import com.example.springbootthymeleaftw.service.UserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registerBusiness")
@RequiredArgsConstructor

public class RegisterBusinessController {

    private final UserValidatorService userValidatorService;
    private final UserService userService;

    @GetMapping()
    public String open(Model model){

        System.out.println(model);
        model.addAttribute("userBusinessForm", new UserBusinessEntity());
        model.addAttribute("userForm", new UserEntity());

        return "registerBusiness";
    }

    @PostMapping()
    public String register(@ModelAttribute("userBusinessForm") UserBusinessEntity userBusinessForm,
                           @ModelAttribute("userForm") UserEntity userForm,
                           BindingResult bindingResult){
        userValidatorService.validate(userForm, bindingResult);
        if (bindingResult.hasErrors())
            return "registerBusiness";


        userBusinessForm.setUserEntity(userForm);
        userBusinessForm.setIsApproved(false);
        userService.saveBusiness(userBusinessForm, userForm);
        userService.login(userForm.getEmail(), userForm.getPassword());
        return "index";
    }
}
