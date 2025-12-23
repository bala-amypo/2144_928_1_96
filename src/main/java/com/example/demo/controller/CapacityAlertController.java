package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
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

    // POST /api/capacity-alerts/analyze?teamName=X&start=Y&end=Z
    @PostMapping("/analyze")
    public ResponseEntity<CapacityAnalysisResultDto> analyzeCapacity(
            @RequestParam String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        // Fix 2: Calling the correct method signature
        CapacityAnalysisResultDto result = capacityAnalysisService.analyzeTeamCapacity(teamName, start, end);
        return ResponseEntity.ok(result);
    }

    // GET /api/capacity-alerts/team/{teamName}?start=Y&end=Z
    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<CapacityAlert>> getAlertsByTeam(
            @PathVariable String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        // Fix 3: Calling the repository method findByTeamNameAndDateBetween
        List<CapacityAlert> alerts = capacityAlertRepository.findByTeamNameAndDateBetween(teamName, start, end);
        return ResponseEntity.ok(alerts);
    }
}