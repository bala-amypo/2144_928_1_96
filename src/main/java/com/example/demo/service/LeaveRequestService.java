package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.EmployeeProfile;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequest request);

    List<LeaveRequest> getLeavesByEmployee(EmployeeProfile employee);

    List<LeaveRequest> getApprovedOverlappingLeaves(
            String teamName,
            LocalDate startDate,
            LocalDate endDate
    );
}
