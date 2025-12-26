package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo, EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    private LeaveRequestDto convertToDto(LeaveRequest leave) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(leave.getId());
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setType(leave.getType());
        dto.setStatus(leave.getStatus());
        dto.setReason(leave.getReason());
        return dto;
    }
    
    // STEP 4.2: create
    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + dto.getEmployeeId()));

        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Invalid Date Range: Start date must be before or equal to end date.");
        }

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(emp);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setType(dto.getType());
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING"); // Default status

        LeaveRequest saved = leaveRepo.save(leave);
        return convertToDto(saved);
    }

    // STEP 4.2: approve
    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found for id: " + id));
        leave.setStatus("APPROVED");
        LeaveRequest updated = leaveRepo.save(leave);
        return convertToDto(updated);
    }

    // STEP 4.2: reject
    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found for id: " + id));
        leave.setStatus("REJECTED");
        LeaveRequest updated = leaveRepo.save(leave);
        return convertToDto(updated);
    }

    // STEP 4.2: getByEmployee
    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employeeId));
        
        return leaveRepo.findByEmployee(emp).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // STEP 4.2: getOverlappingForTeam (uses custom JPA method)
    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(String teamName, LocalDate start, LocalDate end) {
        // Validation check for dates (same as create, although repository will handle actual overlap logic)
        if (start.isAfter(end)) {
            throw new BadRequestException("Invalid Date Range: Start date must be before or equal to end date.");
        }
        
        return leaveRepo.findApprovedOverlappingForTeam(teamName, start, end).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}