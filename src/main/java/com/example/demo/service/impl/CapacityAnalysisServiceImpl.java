package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(
            TeamCapacityConfigRepository c,
            EmployeeProfileRepository e,
            LeaveRequestRepository l,
            CapacityAlertRepository a) {
        this.configRepo = c;
        this.employeeRepo = e;
        this.leaveRepo = l;
        this.alertRepo = a;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        if (start.isAfter(end))
            throw new BadRequestException("Start date invalid or future");

        TeamCapacityConfig config = configRepo.findByTeamName(teamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capacity config not found"));

        if (config.getTotalHeadcount() <= 0)
            throw new BadRequestException("Invalid total headcount");

        Map<LocalDate, Double> map = new HashMap<>();
        boolean risky = false;

        for (LocalDate d : DateRangeUtil.daysBetween(start, end)) {
            int onLeave = leaveRepo
                    .findApprovedOverlappingForTeam(teamName, d, d)
                    .size();

            double capacity = ((double)
                    (config.getTotalHeadcount() - onLeave)
                    / config.getTotalHeadcount()) * 100;

            map.put(d, capacity);

            if (capacity < config.getMinCapacityPercent()) {
                risky = true;
                alertRepo.save(new CapacityAlert(
                        teamName, d, "HIGH", "Low capacity detected"));
            }
        }

        CapacityAnalysisResultDto dto = new CapacityAnalysisResultDto();
        dto.risky = risky;
        dto.capacityByDate = map;
        return dto;
    }
}
