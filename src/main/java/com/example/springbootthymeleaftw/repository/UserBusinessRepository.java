package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;

import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserBusinessRepository extends JpaRepository<UserBusinessEntity, Long> {
    Optional<UserBusinessEntity> findById(Long id);
    Optional<UserBusinessEntity> findByUserEntity(UserEntity userEntity);

}
