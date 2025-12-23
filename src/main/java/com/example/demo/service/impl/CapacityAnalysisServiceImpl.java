package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.stereotype.Service;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    @Override
    public CapacityAnalysisResultDto analyze(String teamName) {
        CapacityAnalysisResultDto dto = new CapacityAnalysisResultDto();
        dto.teamName = teamName;
        dto.capacityOk = true;
        dto.message = "Capacity is sufficient";
        return dto;
    }
}
