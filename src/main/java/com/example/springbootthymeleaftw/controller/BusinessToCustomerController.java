package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.RequestEntity;
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
public class BusinessToCustomerController {
    private final UserService userService;
    @GetMapping("/warehouseBC")
    public String open(Model model){
        model.addAttribute("productsList", userService.getAllProducts() );
        model.addAttribute("request", new RequestEntity());
        return "businessToCustomer/warehouse";
    }
    @PostMapping("/warehouseBC/{productName}/{userBusinessId}")
    public String createRequest(Model model, @ModelAttribute("request") RequestEntity requestEntity,
                           BindingResult bindingResult, @PathVariable String productName){

        requestEntity.setProductName(productName);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            requestEntity.setEmail(email);
            userService.addRequest(requestEntity);
        }
        return open(model);
    }
}
