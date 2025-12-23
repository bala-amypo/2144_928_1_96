package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

public interface CapacityAnalysisService {

    void analyzeTeamCapacity(String teamName,
                             LocalDate start,
                             LocalDate end);

    List<String> getAlerts(String teamName);
}
