package com.example.springbootthymeleaftw.model.entity;
import lombok.Data;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    public UserBusinessEntity() {
    }
}
