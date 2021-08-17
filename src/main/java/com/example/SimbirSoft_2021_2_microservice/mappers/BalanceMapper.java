package com.example.SimbirSoft_2021_2_microservice.mappers;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceMapper {

    BalanceMapper INSTANCE = Mappers.getMapper(BalanceMapper.class);

    BalanceDto toDto(BalanceEntity balanceEntity);
    BalanceEntity toEntity(BalanceDto balanceDto);

}
