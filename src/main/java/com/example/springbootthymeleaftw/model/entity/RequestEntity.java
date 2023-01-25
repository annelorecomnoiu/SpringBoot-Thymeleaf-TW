package com.example.springbootthymeleaftw.model.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="request", schema = "public", catalog = "college")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "product_name")
    private String productName;

    @Basic
    @Column(name = "quantity")
    private Long quantity;

    public RequestEntity() {}
}
