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

import com.example.MigaTattoAgenda.dto.Tattoo.TattooIn;
import com.example.MigaTattoAgenda.dto.Tattoo.TattooOut;
import com.example.MigaTattoAgenda.errors.TattooNotFoundException;
import com.example.MigaTattoAgenda.service.TattooService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/tattoos")
@RequiredArgsConstructor
public class TattooController {
    @Autowired
    private TattooService tattooService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TattooOut createTattoo(@RequestBody TattooIn tattooIn) {
        TattooOut tattooOut = tattooService.saveTattoo(tattooIn);

        return tattooOut;
    }

    @DeleteMapping("/{tattooId}")
    public ResponseEntity<HttpStatus> deleteCostumer(@PathVariable("tattooId") Long tattooId) {
        try {
            tattooService.delete(tattooId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TattooNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byCostumer/{costumerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TattooOut> getTattoosByCostumer(@PathVariable("costumerId") Long costumerId) {
        return tattooService.getTattoosByCostumer(costumerId);
    }
}
