package com.example.springbootthymeleaftw.model.entity;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Collection;

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

    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Basic
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;
    

    public UserEntity() {}

    //    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            /* The table app_users_roles does not need representation in code */
//            name = "app_users_roles",
//            joinColumns = @JoinColumn(
//                    name = "app_user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Collection<RoleEntity> roles;
}