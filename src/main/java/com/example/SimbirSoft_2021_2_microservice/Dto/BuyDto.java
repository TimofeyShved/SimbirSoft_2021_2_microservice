package com.example.SimbirSoft_2021_2_microservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Buy")
public class BuyDto {// ----------------------------------------------- наш с вами пользователь

    // ----------------------------------------------- переменные
    @Schema(description = "email")
    private String email;

    @Schema(description = "password")
    private String password;

    public BuyDto() {
    }

    public BuyDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
