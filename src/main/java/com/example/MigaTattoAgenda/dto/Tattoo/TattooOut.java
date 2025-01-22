package com.example.MigaTattoAgenda.dto.Tattoo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TattooOut {
    public Long id;
    public String tattooName;
    public String image;
    public String description;
    private Long costumerId;
}
