package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/businessUsersList")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;


    @GetMapping()
    public String open(Model model){


        model.addAttribute("businessUsersList", userService.getAllBusinessUsers() );

        return "admin/homeAdmin";
    }


}
