package com.example.MigaTattoAgenda.dto.Tattoo;

import lombok.Data;

@Data
public class TattooIn {
    private String tattooName;
    private String image;
    private String description;
    private Long costumerId;
}
