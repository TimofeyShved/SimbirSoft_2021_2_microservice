package com.example.SimbirSoft_2021_2_microservice.controller;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.UserDto;
import com.example.SimbirSoft_2021_2_microservice.model.BalanceModel;
import com.example.SimbirSoft_2021_2_microservice.model.UserModel;
import com.example.SimbirSoft_2021_2_microservice.service.BalanceService;
import com.example.SimbirSoft_2021_2_microservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// так как порт 8080 был занят, я его поменял на http://localhost:8090/user/ ------- не знаю, как у вас там всё устроено ¯\_(ツ)_/¯
// 1 способ
//@RequiredArgsConstructor
@Tag(name = "Управление Балансом")
@RequestMapping("/balance")
@RestController
public class BalanceController {

    // 2 способ
    //@Autowired
    //private BalanceService balanceService;

    private final BalanceService balanceService;

    // 3 способ
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Operation(summary = "Добавить Баланс")
    @RequestMapping(value = "/posbalance", method = RequestMethod.POST) // создать
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registration(@Validated @RequestBody BalanceDto balanceDto) throws Exception {
        try {
            return ResponseEntity.ok(balanceService.registration(balanceDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получить список балансов")
    @RequestMapping(value = "/getbalances", method = RequestMethod.GET) // взять
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(balanceService.getAll());
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получить баланс выбранного человека")
    @RequestMapping(value = "/getbalance/user/{userId}", method = RequestMethod.GET) // взять
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceDto> getOneByUserId(@Validated @PathVariable Long userId) throws Exception {
        try {
            return ResponseEntity.ok(balanceService.getOneByUserId(userId));
        }catch (Exception e){
            //return  ResponseEntity.badRequest().body(e.getMessage());
            return null;
        }
    }

    @Operation(summary = "Получить баланс выбранного человека")
    @RequestMapping(value = "/getbalance/{balanceId}", method = RequestMethod.GET) // взять
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceModel> getOne(@Validated @PathVariable Long balanceId) throws Exception {
        try {
            return ResponseEntity.ok(balanceService.getOne(balanceId));
        }catch (Exception e){
            //return  ResponseEntity.badRequest().body(e.getMessage());
            return null;
        }
    }

    @Operation(summary = "Удалить выбранного человека")
    @RequestMapping(value = "/deletebalance/{balanceId}", method = RequestMethod.DELETE) // удалить
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteOne(@Validated @PathVariable Long balanceId) throws Exception {
        try {
            return ResponseEntity.ok(balanceService.deleteOne(balanceId));
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновить данные выбранного человека")
    @RequestMapping(value = "/putbalance/{balanceId}", method = RequestMethod.PUT) // обновить
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateOne(@Validated @PathVariable Long balanceId, @Validated @RequestBody BalanceDto balanceDto) throws Exception {
        try {
            return ResponseEntity.ok(balanceService.updateOne(balanceId, balanceDto));
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
