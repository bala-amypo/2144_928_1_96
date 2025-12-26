package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.repository.EmployeeProfileRepository; // <-- ADDED IMPORT
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final CapacityAlertRepository capacityAlertRepository;
    private final EmployeeProfileRepository employeeProfileRepository; // <-- ADDED FIELD TO MATCH TEST EXPECTATION

    @Override
    @Transactional
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        // 1. Validation: Date Range (start <= end and start is not in the past)
        if (start.isAfter(end) || start.isBefore(LocalDate.now())) {
            throw new BadRequestException("Invalid Date Range: Start date or future validation failed.");
        }

        // 2. Load Config
        TeamCapacityConfig rule = teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Missing Config: Capacity config not found."));

        // 3. Validation: Headcount
        if (rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid Headcount: Invalid total headcount.");
        }

        int headcount = rule.getTotalHeadcount();
        double minCapacity = rule.getMinCapacityPercent();
        List<LocalDate> days = DateRangeUtil.daysBetween(start, end);
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;
        
        for (LocalDate date : days) {
            // Find all approved leaves for active employees on this specific date
            List<LeaveRequest> approvedLeaves = leaveRequestRepository.findApprovedOnDate(date);
            
            // Filter down to the target team
            long leavesOnLeave = approvedLeaves.stream()
                .filter(lr -> lr.getEmployee() != null && lr.getEmployee().getTeamName() != null && lr.getEmployee().getTeamName().equals(teamName))
                .count();

            double remainingCapacity = (double) (headcount - leavesOnLeave);
            double capacityPercent = (remainingCapacity / headcount) * 100.0;
            
            capacityByDate.put(date, capacityPercent);

            if (capacityPercent < minCapacity) {
                risky = true;
                String message = String.format("Capacity dropped to %.2f%%. %d/%d available.",
                    capacityPercent, (int)remainingCapacity, headcount);
                
                // Create and save CapacityAlert
                CapacityAlert alert = CapacityAlert.builder()
                    .teamName(teamName)
                    .date(date)
                    .severity("HIGH")
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