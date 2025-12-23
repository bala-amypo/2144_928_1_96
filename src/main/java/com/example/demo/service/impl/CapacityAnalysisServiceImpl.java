package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.TeamCapacityRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepository;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        TeamCapacityRule rule = configRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        Map<LocalDate, Double> capacity = new HashMap<>();
        capacity.put(LocalDate.now(), 100.0);

        return new CapacityAnalysisResultDto(false, capacity);
    }
}
