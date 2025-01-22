package com.example.MigaTattoAgenda.dto;

import lombok.Data;

@Data
public class UserOutDto {
    public String username;
    public String email;

    public UserOutDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
