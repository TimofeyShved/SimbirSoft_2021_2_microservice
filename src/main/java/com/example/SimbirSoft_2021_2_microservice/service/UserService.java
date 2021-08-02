package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// 1 способ
//@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements StandartServiceInterface<UserDto>, UserServiceInterface {
}
