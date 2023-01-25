package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/homeAdmin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;


    @GetMapping("/homeAdmin")
    public String open(Model model){


        model.addAttribute("businessUsersList", userService.getAllBusinessUsers() );

        return "admin/homeAdmin";
    }

    @GetMapping("homeAdmin/accept/{id}")
    public String acceptUserAccount(@PathVariable Long id){
        userService.userIsAccepted(id);
        return "redirect:/homeAdmin";
    }

    @GetMapping("homeAdmin/decline/{id}")
    public String declineUserAccount(@PathVariable Long id){
        userService.userIsDeclined(id);
        return "redirect:/homeAdmin";

    }


}
