package com.example.demo.service.impl;

import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repo;

    public LeaveRequestServiceImpl(LeaveRequestRepository repo) {
        this.repo = repo;
    }

    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        return repo.save(leaveRequest);
    }

    public LeaveRequest getLeaveById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }
}
