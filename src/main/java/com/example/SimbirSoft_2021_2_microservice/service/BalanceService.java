package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.entity.UserEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.mappers.BalanceMapper;
import com.example.SimbirSoft_2021_2_microservice.mappers.UserMapper;
import com.example.SimbirSoft_2021_2_microservice.model.BalanceModel;
import com.example.SimbirSoft_2021_2_microservice.model.UserModel;
import com.example.SimbirSoft_2021_2_microservice.repository.BalanceCrud;
import com.example.SimbirSoft_2021_2_microservice.repository.UserCrud;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.BalanceServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.StandartServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        if ((balanceCrud.findByBalanceId(balanceEntity.getBalanceId())!=null) ||
                (balanceCrud.findByUserId(balanceEntity.getUserId())!=null)){ // проверить, что есть такая реализация существует
            throw new BalanceExistsException();
        }

        // сохраняем
        balanceCrud.save(balanceEntity);
        return BalanceMapper.INSTANCE.toDto(balanceEntity);
    }

    @Transactional
    @Override
    public List<BalanceModel> getAll() throws BalanceNotFoundException {
        List<BalanceEntity> balanceEntityList = balanceCrud.findAll();

        //  проверка на то что вообще существуют
        if (balanceEntityList==null){
            throw new BalanceNotFoundException();
        }

        // перевод коллекции из одного вида в другой
        List<BalanceDto> balanceDtoList = balanceEntityList.stream().map(x-> BalanceMapper.INSTANCE.toDto(x)).collect(Collectors.toList());
        List<BalanceModel> userModelList = balanceDtoList.stream().map(x->new BalanceModel(x)).collect(Collectors.toList());
        return userModelList;
    }

    @Override
    public BalanceDto getOneByUserId(Long id) throws BalanceNotFoundException {
        BalanceEntity balanceEntity = balanceCrud.findByUserId(id);

        //  проверка на то что вообще существуют
        if (balanceEntity==null){
            throw new BalanceNotFoundException();
        }

        return BalanceMapper.INSTANCE.toDto(balanceEntity);
    }

    @Transactional
    @Override
    public BalanceModel getOne(Long id) throws BalanceNotFoundException {
        BalanceEntity balanceEntity = balanceCrud.findByBalanceId(id);

        //  проверка на то что вообще существуют
        if (balanceEntity==null){
            throw new BalanceNotFoundException();
        }

        BalanceModel userModel = new BalanceModel(BalanceMapper.INSTANCE.toDto(balanceEntity));
        return userModel;
    }

    @Transactional
    @Override
    public Long deleteOne(Long id) throws BalanceNotFoundException {

        //  проверка на то что вообще существуют
        if (balanceCrud.findByBalanceId(id)==null){
            throw new BalanceNotFoundException();
        }

        balanceCrud.deleteById(id);
        return id;
    }

    @Transactional
    @Override
    public BalanceDto updateOne(Long id, BalanceDto balanceDto) throws BalanceNotFoundException, BalanceExistsException {

        //  проверка на то что человек вообще существуют
        if (balanceCrud.findByBalanceId(id)==null){
            throw new BalanceNotFoundException();
        }
        BalanceEntity balanceEntityNew = BalanceMapper.INSTANCE.toEntity(balanceDto);
        BalanceEntity balanceEntity = balanceCrud.findByUserId(id);

        //  проверка
        BalanceEntity balanceEntityTest = balanceCrud.findByBalanceIdAndUserId(id, balanceEntityNew.getUserId());
        if ((balanceEntityTest!=null)&&(balanceEntityTest!=balanceEntity)){ // проверить, что есть такая реализация существует
            throw new BalanceExistsException();
        }

        // присваивание новых значений
        balanceEntity.setBalance(balanceEntityNew.getBalance());
        balanceEntity.setUserId(balanceEntityNew.getUserId());

        // сохранение
        balanceCrud.save(balanceEntity);
        return BalanceMapper.INSTANCE.toDto(balanceEntity);
    }
}
