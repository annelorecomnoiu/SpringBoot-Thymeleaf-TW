package com.example.springbootthymeleaftw.controller;


import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final UserService userService;

    @GetMapping()
    public String open(Model model){
        model.addAttribute("productForm", new ProductEntity());
        return "businessToBusiness/createProduct";
    }

    @PostMapping()
    public String addProduct(@ModelAttribute("productForm") ProductEntity productForm,
                           BindingResult bindingResult) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();

            userService.addProductToDB(productForm, email);
        }
            return "/businessToBusiness/homeBB";

    }
}
