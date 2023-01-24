package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.RolesEnum;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.UserRepository;
import com.example.springbootthymeleaftw.service.SecurityService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
//@RequestMapping("/") // this is default
@RequiredArgsConstructor
public class HomeController {
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private UserService userService;

    @GetMapping()
    public String open(Model model, String error, String logout){
        if (!securityService.isAuthenticated()) {
            return "login";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails)principal).getUsername();

            Optional<UserEntity> user = userRepository.findByEmail(email);
            if(user.isPresent()){
                UserEntity currentUser = user.get();
                if(currentUser.getRole().toString() == "CLIENT")
                    return "client/homeClient";
                else if(currentUser.getRole().toString() == "ADMIN")
                    return "admin/homeAdmin";
                else if(currentUser.getRole().toString() == "BUSINESS_TO_BUSINESS")
                    return "businessToBusiness/homeBB";
                else if(currentUser.getRole().toString() == "BUSINESS_TO_CUSTOMER")
                    return "businessToCustomer/homeBC";
            }

        }
        return "index";
    }
}
