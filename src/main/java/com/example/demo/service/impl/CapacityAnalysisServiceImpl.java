package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {
    
    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final CapacityAlertRepository capacityAlertRepository;
    
    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository teamCapacityConfigRepository,
                                      EmployeeProfileRepository employeeProfileRepository,
                                      LeaveRequestRepository leaveRequestRepository,
                                      CapacityAlertRepository capacityAlertRepository) {
        this.teamCapacityConfigRepository = teamCapacityConfigRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.capacityAlertRepository = capacityAlertRepository;
    }
    
    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        // Validate date range
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must be on or before end date");
        }
        
        // Get team capacity config
        TeamCapacityConfig config = teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found for team: " + teamName));
        
        // Validate headcount
        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount. Must be greater than 0");
        }
        
        // Get active employees in team
        List<EmployeeProfile> activeEmployees = employeeProfileRepository.findByTeamNameAndActiveTrue(teamName);
        int activeHeadcount = activeEmployees.size();
        
        // Get overlapping approved leaves
        List<LeaveRequest> overlappingLeaves = leaveRequestRepository
            .findApprovedOverlappingForTeam(teamName, start, end);
        
        // Calculate capacity for each day
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;
        
        List<LocalDate> dates = DateRangeUtil.daysBetween(start, end);
        
        for (LocalDate date : dates) {
            // Count employees on leave on this date
            long onLeaveCount = overlappingLeaves.stream()
                .filter(leave -> !date.isBefore(leave.getStartDate()) && 
                                !date.isAfter(leave.getEndDate()))
                .count();
            
            // Calculate available employees
            long availableCount = activeHeadcount - onLeaveCount;
            
            // Calculate capacity percentage
            double capacityPercent = (activeHeadcount > 0) ? 
                (availableCount * 100.0) / activeHeadcount : 0.0;
            
            capacityByDate.put(date, capacityPercent);
            
            // Check if below minimum capacity
            if (capacityPercent < config.getMinCapacityPercent()) {
                risky = true;
                
                // Create capacity alert
                String severity = (capacityPercent < 50) ? "HIGH" : "MEDIUM";
                String message = String.format(
                    "Team '%s' capacity dropped to %.1f%% on %s (minimum required: %d%%)",
                    teamName, capacityPercent, date, config.getMinCapacityPercent()
                );
                
                CapacityAlert alert = new CapacityAlert(teamName, date, severity, message);
                capacityAlertRepository.save(alert);
            }
        }
        
        return new CapacityAnalysisResultDto(risky, capacityByDate);
    }
}