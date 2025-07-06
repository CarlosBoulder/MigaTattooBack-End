package com.example.MigaTattoAgenda.service;

import com.example.MigaTattoAgenda.dto.Session.LastSessionFullOut;
import com.example.MigaTattoAgenda.dto.Session.SessionInDto;
import com.example.MigaTattoAgenda.dto.Session.SessionOutDto;
import com.example.MigaTattoAgenda.entity.Costumer;
import com.example.MigaTattoAgenda.entity.Session;
import com.example.MigaTattoAgenda.entity.Tattoo;
import com.example.MigaTattoAgenda.errors.CostumerNotFoundException;
import com.example.MigaTattoAgenda.errors.TattooNotFoundException;
import com.example.MigaTattoAgenda.projections.LastSessionProjection;
import com.example.MigaTattoAgenda.repository.CostumerRepository;
import com.example.MigaTattoAgenda.repository.SessionRepository;
import com.example.MigaTattoAgenda.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
        @Autowired
        private SessionRepository sessionRepository;

        @Autowired
        private CostumerRepository costumerRepository;

        @Autowired
        private TattooRepository tattooRepository;

        public SessionOutDto saveSession(SessionInDto sessionInDto) {
                Costumer costumer = costumerRepository.findById(sessionInDto.getCostumerId())
                                .orElseThrow(CostumerNotFoundException::new);

                Tattoo tattoo = tattooRepository.findById(sessionInDto.getTattooId())
                                .orElseThrow(TattooNotFoundException::new);

                Session session = new Session(costumer, tattoo, sessionInDto.getSessionDate());
                session = sessionRepository.save(session);

                return new SessionOutDto(
                                session.getId(),
                                costumer.getId(),
                                costumer.getCostumername(),
                                tattoo.getId(),
                                tattoo.getTattooName(),
                                session.getSessionDate());
        }

        public List<SessionOutDto> getAllSessions() {
                return sessionRepository.findAll().stream().map(session -> new SessionOutDto(
                                session.getId(),
                                session.getCostumer().getId(),
                                session.getCostumer().getCostumername(),
                                session.getTattoo().getId(),
                                session.getTattoo().getTattooName(),
                                session.getSessionDate())).collect(Collectors.toList());
        }

        public LastSessionFullOut findLastSessionByCostumerId(Long costumerId) {
                LastSessionProjection lastSession = sessionRepository
                                .findLastSessionByCostumerId(costumerId)
                                .orElseThrow(() -> new RuntimeException(
                                                "No session found for costumer with ID: " + costumerId));

                return new LastSessionFullOut(
                                lastSession.getCostumer_id(),
                                lastSession.getCostumer_name(),
                                lastSession.getCostumer_lastname(),
                                lastSession.getTattoo_id(),
                                lastSession.getTattoo_name(),
                                lastSession.getSession_date());
        }

        public List<LastSessionFullOut> findLastSessionsOfAllCustomers() {
                List<LastSessionProjection> lastSessions = sessionRepository.findLastSessionsOfAllCustomers();

                if (lastSessions.isEmpty()) {
                        throw new RuntimeException("No sessions found for any customer.");
                }

                return lastSessions.stream()
                                .map(session -> new LastSessionFullOut(
                                                session.getCostumer_id(),
                                                session.getCostumer_name(),
                                                session.getCostumer_lastname(),
                                                session.getTattoo_id(),
                                                session.getTattoo_name(),
                                                session.getSession_date()))
                                .collect(Collectors.toList());
        }
}
