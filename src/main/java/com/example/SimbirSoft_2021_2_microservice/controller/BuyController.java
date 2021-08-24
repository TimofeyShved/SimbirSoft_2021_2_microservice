package com.example.SimbirSoft_2021_2_microservice.controller;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import com.example.SimbirSoft_2021_2_microservice.Dto.BuyDto;
import com.example.SimbirSoft_2021_2_microservice.service.BuyService;
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
@RequestMapping("/buy")
@RestController
public class BuyController {

    // 2 способ
    //@Autowired
    //private BalanceService balanceService;

    private final BuyService buyService;

    // 3 способ
    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    @Operation(summary = "Купить проект")
    @RequestMapping(value = "/postbuy/project", method = RequestMethod.POST) // создать
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buyProject (@Validated @RequestBody BuyDto buyDto) throws Exception {
        try {
            return ResponseEntity.ok(buyService.buyProject(buyDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
