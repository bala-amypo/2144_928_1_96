package com.example.demo.service;

import com.example.demo.entity.CapacityAlert;

import java.time.LocalDate;
import java.util.List;

public interface CapacityAlertService {

    CapacityAlert save(CapacityAlert alert);

    List<CapacityAlert> getByTeamAndDateRange(
            String teamName,
            LocalDate start,
            LocalDate end
    );
}
