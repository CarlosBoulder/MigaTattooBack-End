package com.example.MigaTattoAgenda.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tattoos")
public class Tattoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tattooName", nullable = false, length = 50)
    private String tattooName;

    @Column(name = "image", nullable = true, length = 100)
    private String image;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    private Costumer costumer;

    @OneToMany(mappedBy = "tattoo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions;

    public Tattoo(String tattooName, String image, String description, Costumer costumer) {
        this.tattooName = tattooName;
        this.image = image;
        this.description = description;
        this.costumer = costumer;
    }
}
