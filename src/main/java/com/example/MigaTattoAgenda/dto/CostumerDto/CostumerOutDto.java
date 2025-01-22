package com.example.MigaTattoAgenda.dto.CostumerDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostumerOutDto {
    public Long id;
    public String costumername;
    public String costumerLastname;
    public String phone;
    public String birthDate;
    public String email;
}
