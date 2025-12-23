package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl
        implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequest createLeaveRequest(Long employeeId,
                                           LeaveRequest request) {

        EmployeeProfile employee =
                employeeRepo.findById(employeeId).orElseThrow();

        request.setEmployee(employee);
        request.setStatus("PENDING");

        return leaveRepo.save(request);
    }
}
