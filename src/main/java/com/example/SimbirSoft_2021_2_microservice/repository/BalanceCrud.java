package com.example.SimbirSoft_2021_2_microservice.repository;

import com.example.SimbirSoft_2021_2_microservice.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceCrud extends JpaRepository<BalanceEntity, Long> {
    BalanceEntity findByUserId(Long userId);
    BalanceEntity findByBalanceId(Long balanceId);
}
