package com.example.MigaTattoAgenda.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "costumers")
@NoArgsConstructor
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "costumerName", nullable = false, length = 100)
    private String costumername;

    @Column(name = "costumerLastname", nullable = true, length = 100)
    private String costumerLastname;

    @Column(name = "phone", nullable = true, length = 100)
    private String phone;

    @Column(name = "birthDate", nullable = true, length = 100)
    private String birthDate;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "createdAt", nullable = false, unique = true)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tattoo> tattoos;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;

    public Costumer(String costumername, String costumerLastame, String phone, String birthDate, String email,
            LocalDateTime createdAt) {
        this.costumername = costumername;
        this.costumerLastname = costumerLastame;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.createdAt = createdAt;
    }

}
