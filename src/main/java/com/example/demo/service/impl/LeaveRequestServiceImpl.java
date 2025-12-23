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
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo,
                                   EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    public LeaveRequest create(Long employeeId, LeaveRequest request) {
        EmployeeProfile emp = employeeRepo.findById(employeeId).orElseThrow();
        request.setEmployee(emp);
        request.setStatus("PENDING");
        return leaveRepo.save(request);
    }

    public List<LeaveRequest> getByEmployee(Long employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }
}
