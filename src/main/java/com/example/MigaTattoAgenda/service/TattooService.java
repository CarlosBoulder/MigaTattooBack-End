package com.example.MigaTattoAgenda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MigaTattoAgenda.dto.Tattoo.TattooIn;
import com.example.MigaTattoAgenda.dto.Tattoo.TattooOut;
import com.example.MigaTattoAgenda.entity.Costumer;
import com.example.MigaTattoAgenda.entity.Tattoo;
import com.example.MigaTattoAgenda.errors.CostumerNotFoundException;
import com.example.MigaTattoAgenda.repository.CostumerRepository;
import com.example.MigaTattoAgenda.repository.TattooRepository;

@Service
public class TattooService {
    @Autowired
    TattooRepository tattooRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    public TattooOut saveTattoo(TattooIn tattooIn) {
        Costumer costumer = costumerRepository.findById(tattooIn.getCostumerId())
                .orElseThrow(() -> new CostumerNotFoundException());

        Tattoo tattoo = new Tattoo(tattooIn.getTattooName(), tattooIn.getImage(), tattooIn.getDescription(), costumer);
        tattoo = tattooRepository.save(tattoo);

        return new TattooOut(
                tattoo.getId(),
                tattoo.getTattooName(),
                tattoo.getImage(),
                tattoo.getDescription(),
                costumer.getId());
    }

    public void delete(Long id) {
        Optional<Tattoo> tattoo = tattooRepository.findById(id);
        if (tattoo.isEmpty()) {
            throw new CostumerNotFoundException();
        }

        tattooRepository.deleteById(id);
    }

    public List<TattooOut> getTattoosByCostumer(Long costumerId) {
        List<Tattoo> tattoos = tattooRepository.findByCostumerId(costumerId);
        List<TattooOut> result = new ArrayList<>();

        for (Tattoo tattoo : tattoos) {
            result.add(new TattooOut(
                    tattoo.getId(),
                    tattoo.getTattooName(),
                    tattoo.getImage(),
                    tattoo.getDescription(),
                    tattoo.getCostumer().getId()));
        }
        return result;
    }
}