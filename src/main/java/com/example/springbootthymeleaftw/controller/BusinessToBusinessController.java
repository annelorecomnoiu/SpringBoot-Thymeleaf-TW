package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homeBusinessToBusiness")
@RequiredArgsConstructor
public class BusinessToBusinessController {
    @GetMapping("/homeBusinessToBusiness")
    public String openCreateProduct(Model model){
        model.addAttribute("productForm", new ProductEntity());
        return "/businessToBusiness/createProduct";
    }

    @PostMapping("/homeBusinessToBusiness")
    public String register(@ModelAttribute("productForm") ProductEntity productForm,
                           BindingResult bindingResult){

//        productForm.setProductEntity(productForm);
//        userBusinessForm.setIsApproved(false);
//        userService.saveBusiness(userBusinessForm, userForm);
//        userService.login(userForm.getEmail(), userForm.getPassword());
        return "/businessToBusiness/homeBB";
    }

}
