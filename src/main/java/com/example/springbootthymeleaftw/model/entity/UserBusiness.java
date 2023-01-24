package com.example.springbootthymeleaftw.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserBusiness {

    private Long id;
    private String email;
    private String role;
    private String companyName;
    private String companyAddress;
    private String companyIdentificationCode;
    private Boolean isApproved;

}
