package com.example.SimbirSoft_2021_2_microservice.service.interfaceService;

import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;

public interface UserServiceInterface {
    UserEntity findByEmail(String email) throws UserNotFoundException;
}
