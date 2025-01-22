package com.example.MigaTattoAgenda.dto.CostumerDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostumerInDto {
    private String costumername;
    private String costumerLastname;
    private String phone;
    private String birthDate;
    private String email;
}
