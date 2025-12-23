package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl
        implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    @Override
    public LeaveRequest create(Long employeeId, LeaveRequest request) {
        EmployeeProfile emp =
                employeeRepo.findById(employeeId).orElseThrow();

        request.setEmployee(emp);
        request.setStatus("PENDING");
        return leaveRepo.save(request);
    }

    @Override
    public List<LeaveRequest> getByEmployee(Long employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }
}
