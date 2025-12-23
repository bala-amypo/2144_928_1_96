package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequest create(Long employeeId, LeaveRequest request) {
        EmployeeProfile emp = employeeRepo.findById(employeeId).orElseThrow();
        request.setEmployee(emp);
        request.setStatus("PENDING");
        return leaveRepo.save(request);
    }

    @Override
    public List<LeaveRequest> getByEmployee(Long employeeId) {
        return leaveRepo.findByEmployee_Id(employeeId);
    }
}
