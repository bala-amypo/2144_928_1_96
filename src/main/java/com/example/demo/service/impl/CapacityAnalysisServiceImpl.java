package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository configRepo, EmployeeProfileRepository employeeRepo, LeaveRequestRepository leaveRepo, CapacityAlertRepository alertRepo) {
        this.configRepo = configRepo;
        this.employeeRepo = employeeRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        TeamCapacityConfig config = configRepo.findByTeamName(teamName).orElse(null);
        
        CapacityAnalysisResultDto result = new CapacityAnalysisResultDto();
        result.setRisky(false);
        result.setCapacityByDate(new HashMap<>());
        
        if (config == null) {
            return result; 
        }

        List<EmployeeProfile> employees = employeeRepo.findByTeamNameAndActiveTrue(teamName);
        int totalHeadcount = employees.size();
        if (totalHeadcount == 0) return result; 

        List<LeaveRequest> leaves = leaveRepo.findApprovedOverlappingForTeam(teamName, start, end);
        
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean overallRisky = false;

        List<LocalDate> dates = DateRangeUtil.daysBetween(start, end);
        
        for (LocalDate date : dates) {
            long employeesOnLeave = leaves.stream()
                .filter(l -> !l.getStartDate().isAfter(date) && !l.getEndDate().isBefore(date))
                .count();

            double availableCapacity = (double) (totalHeadcount - employeesOnLeave) / totalHeadcount;
            double capacityPercent = availableCapacity * 100.0;
            capacityByDate.put(date, capacityPercent);
            
            if (capacityPercent < config.getMinCapacityPercent()) {
                overallRisky = true;
                // Create Alert
                CapacityAlert alert = new CapacityAlert();
                alert.setTeamName(teamName);
                alert.setDate(date);
                alert.setSeverity("HIGH"); 
                alert.setMessage(String.format("Capacity dropped to %.1f%% on %s.", capacityPercent, date));
                alertRepo.save(alert);
            }
        }

        result.setRisky(overallRisky);
        result.setCapacityByDate(capacityByDate);
        return result;
    }
}