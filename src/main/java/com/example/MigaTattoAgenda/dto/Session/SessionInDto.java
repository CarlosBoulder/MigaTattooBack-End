package com.example.MigaTattoAgenda.dto.Session;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessionInDto {
    private Long costumerId;
    private Long tattooId;
    private LocalDateTime sessionDate;
}
