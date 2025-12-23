package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.CapacityAlert;
import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl
        implements CapacityAnalysisService {

    private final TeamCapacityRuleRepository repository;

    public CapacityAnalysisServiceImpl(
            TeamCapacityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void analyze() {
        // logic here
    }

    @Override
    public List<String> getAlerts(String teamName) {
        return List.of("Capacity alert for team " + teamName);
    }
}
