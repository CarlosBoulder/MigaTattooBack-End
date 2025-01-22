package com.example.MigaTattoAgenda.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "costumer_id", nullable = false)
    private Costumer costumer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tattoo_id", nullable = false)
    private Tattoo tattoo;

    @Column(name = "session_date", nullable = false)
    private LocalDate sessionDate;

    public Session(Costumer costumer, Tattoo tattoo, LocalDate sessionDate) {
        this.costumer = costumer;
        this.tattoo = tattoo;
        this.sessionDate = sessionDate;
    }
}
