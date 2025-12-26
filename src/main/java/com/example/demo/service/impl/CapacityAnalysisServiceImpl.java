package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamRepository teamRepo;
    private final EmployeeProfileRepository profileRepo;
    private final LeaveRequestRepository leaveRepo;

    public CapacityAnalysisServiceImpl(
            TeamRepository teamRepo,
            EmployeeProfileRepository profileRepo,
            LeaveRequestRepository leaveRepo) {

        this.teamRepo = teamRepo;
        this.profileRepo = profileRepo;
        this.leaveRepo = leaveRepo;
    }

    public List<TeamCapacity> analyze(LocalDate start, LocalDate end) {

        List<TeamCapacity> result = new ArrayList<>();

        for (Team team : teamRepo.findAll()) {
            int onLeave = 0;

            for (EmployeeProfile profile : profileRepo.findByTeamId(team.getId())) {
                for (LeaveRequest leave : leaveRepo.findByEmployeeId(profile.getEmployeeId())) {
                    if (DateRangeUtil.overlaps(
                            leave.getStartDate(), leave.getEndDate(), start, end)) {
                        onLeave++;
                        break;
                    }
                }
            }
            result.add(new TeamCapacity(team.getId(), team.getCapacity() - onLeave));
        }
        return result;
    }
}
