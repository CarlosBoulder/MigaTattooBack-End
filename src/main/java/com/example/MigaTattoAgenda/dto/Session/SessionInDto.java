package com.example.MigaTattoAgenda.dto.Session;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SessionInDto {
    private Long costumerId;
    private Long tattooId;
    private LocalDate sessionDate;
}
