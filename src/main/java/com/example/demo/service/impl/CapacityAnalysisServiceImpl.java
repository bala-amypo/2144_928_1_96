package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.service.CapacityAnalysisService;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final List<String> alerts = new ArrayList<>();

    @Override
    public void analyze() {
        alerts.clear();
        alerts.add("Team A is over capacity");
    }

    @Override
    public List<String> getAlerts(String teamName) {
        return alerts;
    }
}
