package com.example.springbootthymeleaftw.model.entity;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;

@Data
@Entity
@Table(name="user", schema = "public", catalog = "college")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "email", unique = true)
    private String email;

    @Basic
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Basic
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;


    public UserEntity() {}
    
}