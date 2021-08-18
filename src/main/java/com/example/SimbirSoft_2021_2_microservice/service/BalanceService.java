package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.UserExistsException;
import com.example.SimbirSoft_2021_2_microservice.mappers.BalanceMapper;
import com.example.SimbirSoft_2021_2_microservice.mappers.UserMapper;
import com.example.SimbirSoft_2021_2_microservice.repository.BalanceCrud;
import com.example.SimbirSoft_2021_2_microservice.repository.UserCrud;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.BalanceServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.StandartServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 1 способ
//@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceService implements StandartServiceInterface<BalanceDto>, BalanceServiceInterface {


    // 2 способ
    //@Autowired
    //private ProjectCrud projectCRUD;

    private final BalanceCrud balanceCrud; // создаём интерфейс для взаимодействия с бд

    // 3 способ
    public BalanceService(BalanceCrud balanceCrud) {
        this.balanceCrud = balanceCrud;
    }

    @Transactional
    @Override
    public BalanceDto registration(BalanceDto balanceDto) throws BalanceExistsException {
        BalanceEntity balanceEntity = BalanceMapper.INSTANCE.toEntity(balanceDto);

        //  проверка
        if ((balanceCrud.findByBalanceId(balanceEntity.getBalanceId())!=null)){ // проверить, что есть такая реализация существует
            throw new BalanceExistsException();
        }

        // сохраняем
        balanceCrud.save(balanceEntity);
        return BalanceMapper.INSTANCE.toDto(balanceEntity);
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
