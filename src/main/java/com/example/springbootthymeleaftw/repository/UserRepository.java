package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.RolesEnum;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> getAllByRole(RolesEnum rolesEnum);
}
