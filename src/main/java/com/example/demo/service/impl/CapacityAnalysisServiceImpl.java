package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository ruleRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(
            TeamCapacityConfigRepository ruleRepo,
            LeaveRequestRepository leaveRepo,
            CapacityAlertRepository alertRepo) {

        this.ruleRepo = ruleRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        TeamCapacityRule rule = ruleRepo.findByTeamName(teamName)
                .orElseThrow(() ->
                        new RuntimeException("Capacity config not found"));

        Map<LocalDate, Double> capacityMap = new HashMap<>();

        LocalDate date = start;
        while (!date.isAfter(end)) {
            capacityMap.put(date, 100.0);
            date = date.plusDays(1);
        }

        return new CapacityAnalysisResultDto(false, capacityMap);
    }
}
