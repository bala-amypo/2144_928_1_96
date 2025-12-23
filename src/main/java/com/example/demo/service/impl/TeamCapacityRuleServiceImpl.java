package com.example.demo.service.impl;

import com.example.demo.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CapacityAnalysisServiceImpl {

    private final LeaveRequestRepository leaveRepo;

    public CapacityAnalysisServiceImpl(LeaveRequestRepository leaveRepo) {
        this.leaveRepo = leaveRepo;
    }

    public int countApprovedToday() {
        return leaveRepo.findApprovedOnDate(LocalDate.now()).size();
    }
}
