package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestService {

    LeaveRequest create(Long employeeId, LeaveRequest request);
}
