package com.example.SimbirSoft_2021_2_microservice.exception;

public class UserPasswordNotValidateException extends Exception{
    private static final String message = "Ошибка (Error): Пароль не подходит (Password not validate)";
    public UserPasswordNotValidateException(String message) {
        super(message);
    }
    public UserPasswordNotValidateException() {
        super(message);
    }
}
