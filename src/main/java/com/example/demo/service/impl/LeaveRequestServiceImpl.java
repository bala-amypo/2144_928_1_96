package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
// Correct import: using model package
import com.example.demo.model.LeaveRequest; 
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Start date must be on or before endDate.");
        }

        EmployeeProfile employee = employeeProfileRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));

        LeaveRequest request = new LeaveRequest();
        
        // Fix 5 & 6: Setters should be available via Lombok and models imported correctly
        request.setEmployee(employee);
        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setType(dto.getType());
        request.setReason(dto.getReason());
        request.setStatus("PENDING"); // New leaves start as PENDING

        LeaveRequest savedRequest = leaveRequestRepository.save(request);
        // ... mapping and return logic
        return new LeaveRequestDto(); // Placeholder
    }
    
    // ... other methods
}