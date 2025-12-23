package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequest create(Long employeeId, LeaveRequest request);
    List<LeaveRequest> getByEmployee(Long employeeId);
}
