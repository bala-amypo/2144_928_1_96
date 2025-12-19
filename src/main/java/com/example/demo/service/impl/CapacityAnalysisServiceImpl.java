package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.entity.CapacityAlert;
import com.example.demo.entity.TeamCapacityConfig;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(
            TeamCapacityConfigRepository configRepo,
            LeaveRequestRepository leaveRepo,
            CapacityAlertRepository alertRepo) {
        this.configRepo = configRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new BadRequestException("Start date or future");
        }

        TeamCapacityConfig config = configRepo.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }

        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;

        for (LocalDate date : DateRangeUtil.daysBetween(start, end)) {

            int onLeave = leaveRepo.findApprovedOnDate(date).size();
            double capacity = ((double) (config.getTotalHeadcount() - onLeave)
                    / config.getTotalHeadcount()) * 100;

            capacityByDate.put(date, capacity);

            if (capacity < config.getMinCapacityPercent()) {
                risky = true;
                alertRepo.save(new CapacityAlert(
                        teamName, date, "HIGH",
                        "Capacity below threshold"
                ));
            }
        }

        CapacityAnalysisResultDto result = new CapacityAnalysisResultDto();
        result.setRisky(risky);
        result.setCapacityByDate(capacityByDate);
        return result;
    }
}
