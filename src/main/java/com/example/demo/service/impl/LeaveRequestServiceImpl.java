package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public LeaveRequest create(LeaveRequest request) {

        // ✅ FIX: check by employeeId (NOT DB id)
        EmployeeProfile employee = employeeRepo
                .findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        request.setStatus("PENDING");
        return leaveRepo.save(request);
    }

    @Override
    public LeaveRequest getById(Long id) {
        return leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
    }

    @Override
    public List<LeaveRequest> getByEmployee(int employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<LeaveRequest> getAll() {
        return leaveRepo.findAll();
    }

    @Override
    public LeaveRequest updateStatus(Long id, String status) {
        LeaveRequest leave = getById(id);
        leave.setStatus(status);
        return leaveRepo.save(leave);
    }
}
