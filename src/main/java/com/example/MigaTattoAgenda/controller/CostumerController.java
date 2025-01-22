package com.example.MigaTattoAgenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.MigaTattoAgenda.dto.CostumerDto.CostumerInDto;
import com.example.MigaTattoAgenda.dto.CostumerDto.CostumerOutDto;
import com.example.MigaTattoAgenda.errors.CostumerNotFoundException;
import com.example.MigaTattoAgenda.service.CostumerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/costumers")
@RequiredArgsConstructor
public class CostumerController {
    @Autowired
    private CostumerService costumerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerOutDto createCostumer(@RequestBody CostumerInDto costumerInDto) {
        CostumerOutDto costumerOutDto = costumerService.saveCostumer(costumerInDto);

        return costumerOutDto;
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<HttpStatus> deleteCostumer(@PathVariable("costumerId") Long costumerId) {
        try {
            costumerService.delete(costumerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CostumerNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CostumerOutDto> getAllCostumers() {
        return costumerService.getCostumers();
    }

    @GetMapping("/{costumerName}")
    @ResponseStatus(HttpStatus.OK)
    public List<CostumerOutDto> getCostumerByName(@PathVariable("costumerName") String costumerName) {
        return costumerService.getCostumerByName(costumerName);
    }
}
