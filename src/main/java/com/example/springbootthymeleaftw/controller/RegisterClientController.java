package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.RolesEnum;
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

@Controller
@RequestMapping("/registerClient")
@RequiredArgsConstructor
public class RegisterClientController {
    private final UserValidatorService userValidatorService;
    private final UserService userService;

    @GetMapping()
    public String open(Model model){
        System.out.println(model);
        model.addAttribute("userForm", new UserEntity());

        return "registerClient";
    }

    @PostMapping()
    public String register(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult){
        userValidatorService.validate(userForm, bindingResult);
        userForm.setRole(RolesEnum.CLIENT);

        if (bindingResult.hasErrors())
            return "registerClient";

        userService.saveClient(userForm);
        userService.login(userForm.getEmail(), userForm.getPassword());
        return "client/homeClient";
    }
}
