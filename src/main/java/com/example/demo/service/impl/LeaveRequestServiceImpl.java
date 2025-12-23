package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository employeeRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo,
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

    @Override
    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {
        return leaveRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public LeaveRequest updateStatus(Long leaveId, String status) {

        LeaveRequest leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(status);
        return leaveRepo.save(leave);
    }
}
