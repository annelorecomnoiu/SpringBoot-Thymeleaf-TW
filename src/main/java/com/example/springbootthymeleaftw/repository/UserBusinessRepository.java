package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserBusinessRepository extends JpaRepository<UserBusinessEntity, Long> {
    Optional<UserBusinessEntity> findById(Long id);
}
