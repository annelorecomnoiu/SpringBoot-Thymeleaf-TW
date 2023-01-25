package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductName(String productName);

}
