package com.example.MigaTattoAgenda.dto.Session;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastSessionFullOut {
    private Long costumerId;
    private String costumerName;
    private String costumerLastname;
    private Long tattooId;
    private String tattooName;
    private LocalDateTime sessionDate;
}
