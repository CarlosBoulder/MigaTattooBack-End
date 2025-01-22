package com.example.MigaTattoAgenda.errors;

public class CostumerNotFoundException extends RuntimeException {
    public CostumerNotFoundException() {
        super("Costumer not found");
    }
}
