package com.example.demo.service.impl;

import com.example.demo.model.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;

import java.util.List;

public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(CapacityAlertRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public List<CapacityAlert> getAllAlerts() {
        return alertRepo.findAll();
    }
}
