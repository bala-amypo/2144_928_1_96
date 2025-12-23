package com.example.demo.service.impl;

import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAlertService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CapacityAlertServiceImpl implements CapacityAlertService {

    private final CapacityAlertRepository repository;

    public CapacityAlertServiceImpl(CapacityAlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public CapacityAlert save(CapacityAlert alert) {
        return repository.save(alert);
    }

    @Override
    public List<CapacityAlert> getByTeamAndDateRange(
            String teamName,
            LocalDate start,
            LocalDate end) {

        return repository.findByTeamNameAndDateBetween(teamName, start, end);
    }
}
