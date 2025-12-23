package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequest createLeave(Long employeeId, LeaveRequest leave) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setEmployee(employee);
        leave.setStatus("PENDING");

        return leaveRepo.save(leave);
    }
}
