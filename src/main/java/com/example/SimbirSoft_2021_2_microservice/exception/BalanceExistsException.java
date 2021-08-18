package com.example.SimbirSoft_2021_2_microservice.exception;

public class BalanceExistsException extends  Exception{
    private static final String message = "Ошибка (Error): Баланс уже существует (Balance exists)";
    public BalanceExistsException(String message) {
        super(message);
    }
    public BalanceExistsException() {
        super(message);
    }
}
