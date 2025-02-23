package com.example.MigaTattoAgenda.dto.Session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessionOutDto {
    private Long id;
    private Long costumerId;
    private String costumerName;
    private Long tattooId;
    private String tattooName;
    private LocalDateTime sessionDate;
}
