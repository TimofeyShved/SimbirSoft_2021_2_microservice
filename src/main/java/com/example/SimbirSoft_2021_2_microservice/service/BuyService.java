package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BuyDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceInsufficientFundsException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserPasswordNotValidateException;
import com.example.SimbirSoft_2021_2_microservice.repository.BalanceCrud;
import com.example.SimbirSoft_2021_2_microservice.repository.UserCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// 1 способ
//@RequiredArgsConstructor
@Service
@Slf4j
public class BuyService {

    // 2 способ
    //@Autowired
    //private UserCrud userCrud;

    private final BalanceCrud balanceCrud; // создаём интерфейс для взаимодействия с бд
    private final UserCrud userCrud;

    private final Long priceToProject = 1000L;

    // 3 способ
    public BuyService(BalanceCrud balanceCrud, UserCrud userCrud) {
        this.balanceCrud = balanceCrud;
        this.userCrud = userCrud;
    }

    public Object buyProject(BuyDto buyDto) throws UserNotFoundException, UserPasswordNotValidateException, BalanceInsufficientFundsException {
        UserEntity userEntity = userCrud.findByEmail(buyDto.getEmail());

        //  проверка на то что вообще существуют
        if(userEntity==null){
            throw new UserNotFoundException();
        }
        //  проверка
        if (userEntity.getPassword().equals(buyDto.getPassword())){
            throw new UserPasswordNotValidateException();
        }
        BalanceEntity balanceEntity = balanceCrud.findByUserId(userEntity.getUserId());
        if (balanceEntity.getBalance()<priceToProject){
            throw new BalanceInsufficientFundsException();
        }

        return null;
    }

}
