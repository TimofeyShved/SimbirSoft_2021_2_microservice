package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.BalanceNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserExistsException;
import com.example.SimbirSoft_2021_2_microservice.exception.UserNotFoundException;
import com.example.SimbirSoft_2021_2_microservice.mappers.BalanceMapper;
import com.example.SimbirSoft_2021_2_microservice.model.BalanceModel;
import com.example.SimbirSoft_2021_2_microservice.repository.BalanceCrud;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.BalanceServiceInterface;
import com.example.SimbirSoft_2021_2_microservice.service.interfaceService.StandartServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1> Сервис баланса - (BalanceService) </h1>
 * Данный класс реализует запросы, которые
 * приходят в контроллер баланса (BalanceController),
 * результат он возвращаяет обратно.
 * <p>
 * <b>Примечание:</b>
 * В данном классе можно конструтор, организовать 3
 * разными способами.
 * А так же он использует свой интерфейс.
 * И стандартный для этого проекта, а это:
 * регистрация (registration),
 * вытащить всё (getAll),
 * вытащить одно (getOne),
 * удалить одно (deleteOne),
 * обновить одно (updateOne).
 *
 * @автор  Швед Т.Ю.
 * @версия 0.4
 * @от   2021-08-13
 */

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

    /**
     * Это основной метод регистрации, из стандартного интерфейса
     * использующий метод registration.
     * Основная задача которой сохранить нового пользователя в бд.
     * @param balanceDto Это первый и единственный параметр метода registration, который обозначает баланс пользователя.
     * @return BalanceDto Вернёт Баланс.
     * @exception BalanceExistsException При ошибке если такая реализация существует.
     */
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

    /**
     * Это основной метод вытащить всё, из стандартного интерфейса
     * использующий метод getAll.
     * Основная задача которой вытащить баланс, всех людей из бд.
     * @param () Не используется.
     * @return List<BalanceModel> Вернёт список баланса всех людей.
     * @exception BalanceNotFoundException При ошибке если баланса вообще не существуют.
     */
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

    /**
     * Это дополнительный метод, что-бы вытащить баланс одного человека, из стандартного интерфейса
     * использующий метод getOneByUserId.
     * Основная задача которой вытащить баланс одного человека по его id из бд.
     * @param id Это первый и единственный параметр метода getOneByUserId, который обозначает номер пользователя в бд.
     * @return BalanceDto Вернёт баланс пользователя.
     * @exception BalanceNotFoundException При ошибке если баланса вообще не существуют.
     */
    @Override
    public BalanceDto getOneByUserId(Long id) throws BalanceNotFoundException {
        BalanceEntity balanceEntity = balanceCrud.findByUserId(id);

        //  проверка на то что вообще существуют
        if (balanceEntity==null){
            throw new BalanceNotFoundException();
        }

        return BalanceMapper.INSTANCE.toDto(balanceEntity);
    }

    /**
     * Это основной метод, что-бы вытащить баланс одного человека, из стандартного интерфейса
     * использующий метод getOne.
     * Основная задача которой вытащить баланс одного человека из бд.
     * @param id Это первый и единственный параметр метода getOne, который обозначает номер баланса пользователя в бд.
     * @return BalanceModel Вернёт баланс пользователя.
     * @exception BalanceNotFoundException При ошибке если баланс вообще не существуют.
     */
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

    /**
     * Это основной метод, что-бы удалить баланс одного человека, из стандартного интерфейса
     * использующий метод deleteOne.
     * Основная задача которой удалить одного человека из бд.
     * @param id Это первый и единственный параметр метода deleteOne, который обозначает номер баланса пользователя в бд.
     * @return Long Вернёт номер баланса пользователя в бд.
     * @exception BalanceNotFoundException При ошибке если баланс вообще не существуют.
     */
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

    /**
     * Это дополнительный метод удалить баланс одного человека, из стандартного интерфейса
     * использующий метод deleteOneByUserId.
     * Основная задача которой удалить баланс одного человека по его id из бд.
     * @param userId Это первый и единственный параметр метода deleteOneByUserId, который обозначает номер пользователя в бд.
     * @return Long Вернёт номер пользователя в бд.
     */
    @Transactional
    @Override
    public Long deleteOneByUserId(Long userId) {
        BalanceEntity balanceEntity = balanceCrud.findByUserId(userId);
        if (balanceEntity!=null){
            balanceCrud.delete(balanceEntity);
        }
        return userId;
    }

    /**
     * Это основной метод, что-бы обновить баланс одного человека, из стандартного интерфейса
     * использующий метод updateOne.
     * Основная задача которой обновить баланс одного человека в бд.
     * @param id Это первый параметр метода updateOne, который обозначает номер баланса пользователя в бд.
     * @param balanceDto Это второй параметр метода updateOne, который обозначает данные баланса пользователя.
     * @return BalanceDto Вернёт баланс пользователя.
     * @exception BalanceNotFoundException При ошибке если люди вообще не существуют.
     * @exception BalanceExistsException При ошибке если такая реализация существует.
     */
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
