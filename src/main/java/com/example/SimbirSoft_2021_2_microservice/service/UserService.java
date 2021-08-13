package com.example.SimbirSoft_2021_2_microservice.service;

import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
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
public class UserService implements StandartServiceInterface<UserDto>, UserServiceInterface {

    // 2 способ
    //@Autowired
    //private ProjectCrud projectCRUD;

    private final UserCrud userCrud; // создаём интерфейс для взаимодействия с бд
    private final RoleService roleService;

    // 3 способ
    public UserService(UserCrud userCrud, RoleService roleService) {
        this.userCrud = userCrud;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public UserDto registration(UserDto userDto) throws UserExistsException {
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);

        //  проверка
        if ((userCrud.findByEmail(userEntity.getEmail())!=null)){ // проверить, что есть такая реализация существует
            throw new UserExistsException();
        }

        // сохраняем
        userCrud.save(userEntity);
        return UserMapper.INSTANCE.toDto(userEntity);
    }

    @Transactional
    @Override
    public List<UserModel> getAll() throws UserNotFoundException {
        List<UserEntity> userEntityList = userCrud.findAll();

        //  проверка на то что люди вообще существуют
        if (userEntityList==null){
            throw new UserNotFoundException();
        }

        // перевод коллекции из одного вида в другой
        List<UserDto> userDtoList = userEntityList.stream().map(x-> UserMapper.INSTANCE.toDto(x)).collect(Collectors.toList());
        List<UserModel> userModelList = userDtoList.stream().map(x->new UserModel(x)).collect(Collectors.toList());
        return userModelList;
    }

    @Transactional
    @Override
    public UserModel getOne(Long id) throws UserNotFoundException {
        UserEntity userEntity = userCrud.findByUserId(id);

        //  проверка на то что человек вообще существуют
        if (userEntity==null){
            throw new UserNotFoundException();
        }

        UserModel userModel = new UserModel(UserMapper.INSTANCE.toDto(userEntity));
        return userModel;
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity userEntity = userCrud.findByEmail(email);
        return userEntity;
    }

    @Transactional
    @Override
    public Long deleteOne(Long id) throws UserNotFoundException, RoleNotFoundException {

        //  проверка на то что человек вообще существуют
        if (userCrud.findByUserId(id)==null){
            throw new UserNotFoundException();
        }

        roleService.deleteByUserId(id);
        userCrud.deleteById(id);
        return id;
    }

    @Transactional
    @Override
    public UserDto updateOne(Long id, UserDto userDto) throws UserNotFoundException, UserExistsException {

        //  проверка на то что человек вообще существуют
        if (userCrud.findByUserId(id)==null){
            throw new UserNotFoundException();
        }
        UserEntity userEntityNew = UserMapper.INSTANCE.toEntity(userDto);
        UserEntity userEntity = userCrud.findByUserId(id);

        //  проверка
        if ((userCrud.findByEmail(userEntityNew.getEmail())!=null)){ // проверить, что есть такая реализация существует
            throw new UserExistsException();
        }

        // присваивание новых значений
        userEntity.setFirstName(userEntityNew.getFirstName());
        userEntity.setLastName(userEntityNew.getLastName());
        userEntity.setPatronymic(userEntityNew.getPatronymic());
        userEntity.setEmail(userEntityNew.getEmail());
        userEntity.setPassword(userEntityNew.getPassword());

        // сохранение
        userCrud.save(userEntity);
        return UserMapper.INSTANCE.toDto(userEntity);
    }
}

