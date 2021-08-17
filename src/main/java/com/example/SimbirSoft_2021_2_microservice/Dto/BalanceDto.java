package com.example.SimbirSoft_2021_2_microservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Баланс")
public class BalanceDto { // ----------------------------------------------- наш с вами пользователь

    // ----------------------------------------------- переменные
    @Schema(description = "Id баланса")
    private Long balanceId;

    @Schema(description = "Баланс")
    private String balance;

    @Schema(description = "Id человека")
    private Long userId;

    public BalanceDto() {
    }

    public BalanceDto(Long balanceId, String balance, Long userId) {
        this.balanceId = balanceId;
        this.balance = balance;
        this.userId = userId;
    }

    // ----------------------------------------------- гетеры и сетеры


    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}