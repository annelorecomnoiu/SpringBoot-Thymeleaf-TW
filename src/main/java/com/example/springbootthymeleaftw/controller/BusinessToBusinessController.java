package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BusinessToBusinessController {

    private final UserService userService;
    @GetMapping("/warehouse")
    public String openWarehouse(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            userService.findIdByEmail(email);
            model.addAttribute("productsList", userService.getAllFilteredProducts( userService.findIdByEmail(email)));
        }

        return "businessToBusiness/warehouse";
    }

    @GetMapping("/requests")
    public String openRequests(Model model){
        model.addAttribute("requestsList", userService.getAllRequests());
        return "businessToBusiness/bcRequests";
    }


    @GetMapping("requests/acceptRequest/{name}/{id}")
    public String acceptRequest(Model model, @PathVariable String name, @PathVariable Long id){
        userService.transaction(name,id);
        return openRequests(model);
    }

    @GetMapping("requests/declineRequest/{id}")
    public String declineRequest(Model model, @PathVariable Long id){
        userService.declineRequestById(id);
        return openRequests(model);
    }







}
