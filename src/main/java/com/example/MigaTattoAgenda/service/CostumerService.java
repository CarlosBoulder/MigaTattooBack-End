package com.example.MigaTattoAgenda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MigaTattoAgenda.dto.CostumerDto.CostumerInDto;
import com.example.MigaTattoAgenda.dto.CostumerDto.CostumerOutDto;
import com.example.MigaTattoAgenda.entity.Costumer;
import com.example.MigaTattoAgenda.errors.CostumerNotFoundException;
import com.example.MigaTattoAgenda.projections.CostumerProjection;
import com.example.MigaTattoAgenda.repository.CostumerRepository;

@Service
public class CostumerService {
    @Autowired
    CostumerRepository costumerRepository;

    public CostumerOutDto saveCostumer(CostumerInDto costumerInDto) {
        Costumer costumer = costumerRepository
                .save(new Costumer(costumerInDto.getCostumername(), costumerInDto.getCostumerLastname(),
                        costumerInDto.getPhone(), costumerInDto.getBirthDate(), costumerInDto.getEmail()));

        return new CostumerOutDto(costumer.getId(), costumer.getCostumerLastname(), costumer.getPhone(),
                costumer.getBirthDate(), costumer.getEmail(), costumer.getCostumername());
    }

    public void delete(Long id) {
        Optional<Costumer> costumer = costumerRepository.findById(id);
        if (costumer.isEmpty()) {
            throw new CostumerNotFoundException();
        }

        costumerRepository.deleteById(id);
    }

    public List<CostumerOutDto> getCostumers() {
        List<Costumer> data = costumerRepository.findAll();
        List<CostumerOutDto> result = new ArrayList<>();

        for (Costumer costumer : data) {
            Long id = costumer.getId();
            String costumername = costumer.getCostumername();
            String costumerLastname = costumer.getCostumerLastname();
            String phone = costumer.getPhone();
            String birthDate = costumer.getBirthDate();
            String email = costumer.getEmail();

            CostumerOutDto costumerOut = new CostumerOutDto(id, costumername, costumerLastname, phone, birthDate,
                    email);
            result.add(costumerOut);
        }
        return result;
    }

    public List<CostumerOutDto> getCostumerByName(String costumerName) {
        List<CostumerProjection> data = costumerRepository.findCostumerByName(costumerName);
        List<CostumerOutDto> result = new ArrayList<>();

        for (CostumerProjection costumer : data) {
            Long id = costumer.getId();
            String costumername = costumer.getCostumer_name();
            String costumerLastname = costumer.getCostumer_lastname();
            String phone = costumer.getPhone();
            String birthDate = costumer.getBirth_date();
            String email = costumer.getEmail();

            CostumerOutDto costumerOut = new CostumerOutDto(id, costumername, costumerLastname, phone, birthDate,
                    email);
            result.add(costumerOut);
        }
        return result;
    }

    public CostumerOutDto editCostumer(Long id, CostumerInDto costumerIn) throws Exception {
        Optional<Costumer> costumerToEdit = costumerRepository.findById(id);

        if (costumerToEdit.isEmpty()) {
            throw new CostumerNotFoundException();
        }

        Costumer costumerToSave = costumerToEdit.get();
        costumerToSave.setCostumername(costumerIn.getCostumername());
        costumerToSave.setCostumerLastname(costumerIn.getCostumerLastname());
        costumerToSave.setPhone(costumerIn.getPhone());
        costumerToSave.setBirthDate(costumerIn.getBirthDate());
        costumerToSave.setEmail(costumerIn.getEmail());

        costumerRepository.save(costumerToSave);

        return new CostumerOutDto(costumerToSave.getId(), costumerToSave.getCostumername(),
                costumerToSave.getCostumerLastname(), costumerToSave.getPhone(), costumerToSave.getBirthDate(),
                costumerToSave.getEmail());
    }
}
