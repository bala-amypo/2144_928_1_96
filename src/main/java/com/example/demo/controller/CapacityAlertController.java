package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
@Tag(name = "Capacity Alerts", description = "Capacity Analysis and Alert APIs")
public class CapacityAlertController {
    
    private final CapacityAnalysisService capacityAnalysisService;
    private final CapacityAlertRepository capacityAlertRepository;
    
    public CapacityAlertController(CapacityAnalysisService capacityAnalysisService,
                                  CapacityAlertRepository capacityAlertRepository) {
        this.capacityAnalysisService = capacityAnalysisService;
        this.capacityAlertRepository = capacityAlertRepository;
    }
    
    @PostMapping("/analyze")
    @Operation(summary = "Analyze team capacity for a date range")
    public ResponseEntity<CapacityAnalysisResultDto> analyzeTeamCapacity(
            @RequestParam String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        CapacityAnalysisResultDto result = capacityAnalysisService.analyzeTeamCapacity(teamName, start, end);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get capacity alerts for a team")
    public ResponseEntity<List<CapacityAlert>> getAlertsByTeam(
            @PathVariable String teamName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        List<CapacityAlert> alerts;
        if (start != null && end != null) {
            alerts = capacityAlertRepository.findByTeamNameAndDateBetween(teamName, start, end);
        } else {
            alerts = capacityAlertRepository.findByTeamName(teamName);
        }
        
        return ResponseEntity.ok(alerts);
    }
}