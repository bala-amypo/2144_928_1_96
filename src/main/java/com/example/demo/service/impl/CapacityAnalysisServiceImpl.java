package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final CapacityAlertRepository capacityAlertRepository;

    @Override
    @Transactional
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        // Validate date range
        if (start.isAfter(end) || start.isBefore(LocalDate.now())) {
            throw new BadRequestException("Start date or future validation failed. Start date must be today or in the future and on or before end date.");
        }
        
        // Load capacity config (Fix 4: Accessing the rule model)
        TeamCapacityConfig rule = teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found for team: " + teamName));

        if (rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount: " + rule.getTotalHeadcount());
        }

        List<LocalDate> days = DateRangeUtil.daysBetween(start, end);
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;
        
        // Clear previous alerts for the range
        capacityAlertRepository.findByTeamNameAndDateBetween(teamName, start, end).forEach(capacityAlertRepository::delete);

        for (LocalDate date : days) {
            // Find approved leaves for this team that overlap with the day
            // We use the simpler findApprovedOnDate here for daily calculation
            long leavesOnLeave = leaveRequestRepository.findApprovedOnDate(date)
                .stream()
                .filter(lr -> lr.getEmployee().getTeamName().equals(teamName))
                .count();

            // Total active employees for team is the totalHeadcount from the rule
            int headcount = rule.getTotalHeadcount();
            
            // Calculate remaining capacity
            double remainingCapacity = (double) (headcount - leavesOnLeave);
            double capacityPercent = (remainingCapacity / headcount) * 100.0;
            
            capacityByDate.put(date, capacityPercent);

            // Check for capacity breach and create alert
            if (capacityPercent < rule.getMinCapacityPercent()) {
                risky = true;
                String message = String.format(
                    "Capacity dropped to %.2f%% (Min required: %d%%). %d/%d available.",
                    capacityPercent, rule.getMinCapacityPercent(), (int)remainingCapacity, headcount
                );
                
                CapacityAlert alert = CapacityAlert.builder()
                    .teamName(teamName)
                    .date(date)
                    .severity("HIGH") // Simple logic: any breach is HIGH
                    .message(message)
                    .build();
                
                capacityAlertRepository.save(alert);
            }
        }

        return CapacityAnalysisResultDto.builder()
            .risky(risky)
            .capacityByDate(capacityByDate)
            .build();
    }
}