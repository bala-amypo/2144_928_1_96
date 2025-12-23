package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;
import java.time.LocalDate;

public interface CapacityAnalysisService {
    // Fix 17: Service method MUST match the controller's call
    CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end); 
    // You also need a method to get alerts for the CapacityAlertController
    // List<CapacityAlert> getAlertsByTeam(String teamName, LocalDate start, LocalDate end);
}