package com.example.SimbirSoft_2021_2_microservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="balance_entity")
public class BalanceEntity {

    // ----------------------------------------------- переменные
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автогенерация значений ключа
    @Column(name = "balance_id")
    private Long balanceId;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "user_id")
    private Long userId;

    public BalanceEntity() {
    }

    public BalanceEntity(Long balanceId, Long balance, Long userId) {
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
