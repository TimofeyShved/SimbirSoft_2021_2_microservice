package com.example.SimbirSoft_2021_2_microservice.exception;

public class BalanceInsufficientFundsException extends Exception{
    private static final String message = "Ошибка (Error): На балансе недостаточно средств (Balance insufficient funds)";
    public BalanceInsufficientFundsException(String message) {
        super(message);
    }
    public BalanceInsufficientFundsException() {
        super(message);
    }
}
