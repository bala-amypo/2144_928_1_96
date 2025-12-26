package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest getLeaveById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }
}
