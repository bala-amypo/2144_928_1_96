package com.example.demo.service.impl;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo) {
        this.leaveRepo = leaveRepo;
    }

    @Override
    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus("PENDING");
        return leaveRepo.save(request);
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployee(EmployeeProfile employee) {
        // ✅ THIS METHOD NOW EXISTS
        return leaveRepo.findByEmployee(employee);
    }

    @Override
    public List<LeaveRequest> getApprovedOverlappingLeaves(
            String teamName,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return leaveRepo.findApprovedOverlappingForTeam(
                teamName, startDate, endDate
        );
    }
}
