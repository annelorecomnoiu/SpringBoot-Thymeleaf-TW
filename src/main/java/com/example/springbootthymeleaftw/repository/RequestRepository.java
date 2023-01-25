package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RequestRepository extends JpaRepository<RequestEntity, Long> {
}
