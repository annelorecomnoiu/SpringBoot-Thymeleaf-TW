package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class BusinessToBusinessController {

    private final UserService userService;
    @GetMapping()
    public String openWarehouse(Model model){
        model.addAttribute("productsList", userService.getAllProducts() );
        return "businessToBusiness/warehouse";
    }

    @GetMapping("warehouse/editQuantity/{id}")
    public String editQuantity(@PathVariable Long id){
        //userService.userIsAccepted(id);
        return "businessToBusiness/warehouse";
    }






}
