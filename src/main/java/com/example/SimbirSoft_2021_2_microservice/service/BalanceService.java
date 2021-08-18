package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.BalanceServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.StandartServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.UserServiceInterface;

public class BalanceService implements StandartServiceInterface<BalanceDto>, BalanceServiceInterface {
    @Override
    public BalanceDto registration(BalanceDto o) throws Exception {
        return null;
    }

    @Override
    public <S> S getAll() throws Exception {
        return null;
    }

    @Override
    public <S> S getOne(Long id) throws Exception {
        return null;
    }

    @Override
    public <S> S deleteOne(Long id) throws Exception {
        return null;
    }

    @Override
    public BalanceDto updateOne(Long id, BalanceDto o) throws Exception {
        return null;
    }
}
