package com.example.springbootthymeleaftw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/businessToBusiness")
public class BusinessToCustomerController {

    @GetMapping("/")
    public String home()
    {
        return "/businessToCustomer/homeBC";
    }
}
