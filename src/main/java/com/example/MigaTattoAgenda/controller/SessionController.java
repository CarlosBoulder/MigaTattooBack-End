package com.example.MigaTattoAgenda.controller;

import com.example.MigaTattoAgenda.dto.Session.SessionInDto;
import com.example.MigaTattoAgenda.dto.Session.SessionOutDto;
import com.example.MigaTattoAgenda.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sessions")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200" })
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionOutDto createSession(@RequestBody SessionInDto sessionInDto) {
        return sessionService.saveSession(sessionInDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SessionOutDto> getAllSessions() {
        return sessionService.getAllSessions();
    }
}
