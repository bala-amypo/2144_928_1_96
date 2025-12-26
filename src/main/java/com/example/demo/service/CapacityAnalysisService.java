package com.example.demo.service;

import com.example.demo.model.TeamCapacity;
import java.time.LocalDate;
import java.util.List;

public interface CapacityAnalysisService {
    List<TeamCapacity> analyze(LocalDate start, LocalDate end);
}
