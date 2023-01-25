package com.example.springbootthymeleaftw.model.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product", schema = "public", catalog = "college")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_business_id", nullable = false)
    private Long userBusinessId;

    @Basic
    @Column(name = "product_name")
    private String productName;

    @Basic
    @Column(name = "product_color")
    private String productColor;

    @Basic
    @Column(name = "product_weight")
    private String productWeight;

    @Basic
    @Column(name = "product_quantity_bb")
    private Long productQuantityBB;

    @Basic
    @Column(name = "product_quantity_bc")
    private Long productQuantityBC;

    public ProductEntity() {}


}
