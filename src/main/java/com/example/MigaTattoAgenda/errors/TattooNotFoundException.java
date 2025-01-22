package com.example.MigaTattoAgenda.errors;

public class TattooNotFoundException extends RuntimeException {
    public TattooNotFoundException() {
        super("Tattoo not found");
    }
}
