package com.example.MigaTattoAgenda.dto.Session;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastSessionOut {
    private Long costumerId;
    private Long tattooId;
    private LocalDateTime sessionDate;
}
