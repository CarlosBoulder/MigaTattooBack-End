package com.example.MigaTattoAgenda.dto.Session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SessionOutDto {
    private Long id;
    private Long costumerId;
    private String costumerName;
    private Long tattooId;
    private String tattooName;
    private LocalDate sessionDate;
}
