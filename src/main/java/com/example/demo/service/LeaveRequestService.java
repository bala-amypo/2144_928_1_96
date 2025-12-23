package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest create(Long employeeId, LeaveRequest request);

    LeaveRequest getById(Long id);

    List<LeaveRequest> getByEmployee(Long employeeId);

    List<LeaveRequest> getAll();

    LeaveRequest updateStatus(Long id, String status);
}
