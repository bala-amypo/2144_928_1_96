package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert; // CORRECTED IMPORT
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
@RequiredArgsConstructor
public class CapacityAlertController {

    private final CapacityAnalysisService capacityAnalysisService;
    private final CapacityAlertRepository capacityAlertRepository;

    @PostMapping("/analyze")
    public ResponseEntity<CapacityAnalysisResultDto> analyzeCapacity(
            @RequestParam String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        // Fix 17: Calling the correct method signature (String, LocalDate, LocalDate)
        CapacityAnalysisResultDto result = capacityAnalysisService.analyzeTeamCapacity(teamName, start, end);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<CapacityAlert>> getAlertsByTeam( // Fix 18: Correctly uses List<CapacityAlert>
            @PathVariable String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        List<CapacityAlert> alerts = capacityAlertRepository.findByTeamNameAndDateBetween(teamName, start, end);
        return ResponseEntity.ok(alerts);
    }
}