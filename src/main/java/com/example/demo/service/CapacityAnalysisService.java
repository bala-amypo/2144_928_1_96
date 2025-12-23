package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;

public interface CapacityAnalysisService {
    CapacityAnalysisResultDto analyze(String teamName);
}
