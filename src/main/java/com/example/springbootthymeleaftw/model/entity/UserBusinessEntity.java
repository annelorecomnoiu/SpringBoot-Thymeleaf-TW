package com.example.springbootthymeleaftw.model.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="user_business", schema = "public", catalog = "college")
public class UserBusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "company_name")
    private String companyName;

    @Basic
    @Column(name = "company_address")
    private String companyAddress;

    @Basic
    @Column(name = "company_identification_code")
    private String companyIdentificationCode;

    @Basic
    @Column(name = "is_approved")
    private Boolean isApproved;


    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_business_id")
    private List<ProductEntity> listOfProducts = new ArrayList<>();


    public UserBusinessEntity() {
    }

}
