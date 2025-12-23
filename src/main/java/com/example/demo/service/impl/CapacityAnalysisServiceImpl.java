package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert; // Fix 5, 6, 36
import com.example.demo.model.TeamCapacityConfig; // Fix 5, 6, 32
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService; // Fix 22, 23
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
// Fix 22: Must implement the three-argument method
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final CapacityAlertRepository capacityAlertRepository;

    @Override // Fix 23: Must override the interface method
    @Transactional
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        // Validation with required messages
        if (start.isAfter(end) || start.isBefore(LocalDate.now())) {
            throw new BadRequestException("Invalid Date Range: Start date or future validation failed.");
        }
        
        // Fix 32: Correct model reference
        TeamCapacityConfig rule = teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Missing Config: Capacity config not found."));

        if (rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid Headcount: Invalid total headcount.");
        }

        List<LocalDate> days = DateRangeUtil.daysBetween(start, end);
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;
        
        for (LocalDate date : days) {
            // Fix 34: findApprovedOnDate must exist on the repository
            long leavesOnLeave = leaveRequestRepository.findApprovedOnDate(date)
                .stream()
                .filter(lr -> lr.getEmployee().getTeamName().equals(teamName))
                .count();

            int headcount = rule.getTotalHeadcount();
            double remainingCapacity = (double) (headcount - leavesOnLeave);
            double capacityPercent = (remainingCapacity / headcount) * 100.0;
            
            capacityByDate.put(date, capacityPercent);

            if (capacityPercent < rule.getMinCapacityPercent()) {
                risky = true;
                String message = String.format("Capacity dropped to %.2f%%. %d/%d available.",
                    capacityPercent, (int)remainingCapacity, headcount);
                
                // Fix 36: Correct static builder and class reference
                CapacityAlert alert = CapacityAlert.builder()
                    .teamName(teamName)
                    .date(date)
                    .severity("HIGH")
                    .message(message)
                    .build();
                
                capacityAlertRepository.save(alert);
            }
        }

        // Fix 38: Correct static builder access
        return CapacityAnalysisResultDto.builder()
            .risky(risky)
            .capacityByDate(capacityByDate)
            .build();
    }
}