package com.example.SimbirSoft_2021_2_microservice.repository;

import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrud extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long userId);
    UserEntity findByFirstNameAndLastName(String firstName, String lastName);
    UserEntity findByEmail(String email);
}
