package com.example.SimbirSoft_2021_2_microservice.model;

import com.example.SimbirSoft_2021_2_microservice.Dto.BalanceDto;
import lombok.Data;

@Data
public class BalanceModel {

    // ----------------------------------------------- переменные
    private Long balanceId;
    private Long userId;

    public BalanceModel() {
    }

    public BalanceModel(Long balanceId, Long userId) {
        this.balanceId = balanceId;
        this.userId = userId;
    }

    public BalanceModel(BalanceDto x) {
        this.balanceId = x.getBalanceId();
        this.userId = x.getUserId();
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}