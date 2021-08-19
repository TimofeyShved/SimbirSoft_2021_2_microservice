package com.example.SimbirSoft_2021_2_microservice.exception;

public class BalanceNotFoundException extends  Exception{
    private static final String message = "Ошибка (Error): Баланс не найден (Balance not found)";
    public BalanceNotFoundException(String message) {
        super(message);
    }
    public BalanceNotFoundException() {
        super(message);
    }
}
