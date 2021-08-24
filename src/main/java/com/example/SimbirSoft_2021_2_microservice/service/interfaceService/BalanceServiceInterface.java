package com.example.SimbirSoft_2021_2_microservice.service.interfaceService;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;

public interface BalanceServiceInterface {
    BalanceDto getOneByUserId(Long userId) throws Exception;
    Long deleteOneByUserId(Long userId);
}
