package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    @Override
    @Transactional
    public LeaveRequestDto create(LeaveRequestDto dto) {
        // 1. Validate employee
        EmployeeProfile employee = employeeProfileRepository.findByEmployeeId(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));

        // 2. Validate date range: startDate after endDate
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Invalid Date Range: Start date after end date.");
        }
        
        // 3. Validation rule: Start date cannot be in the past
        if (dto.getStartDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Invalid Date Range: Start date or future validation failed.");
        }

        LeaveRequest request = LeaveRequest.builder()
                .employee(employee)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .type(dto.getType())
                .status("PENDING")
                .reason(dto.getReason())
                .build();

        LeaveRequest savedRequest = leaveRequestRepository.save(request);
        return convertToDto(savedRequest);
    }

    @Override
    @Transactional
    public LeaveRequestDto approve(Long id) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));

        request.setStatus("APPROVED");
        return convertToDto(leaveRequestRepository.save(request));
    }

    @Override
    @Transactional
    public LeaveRequestDto reject(Long id) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));

        request.setStatus("REJECTED");
        return convertToDto(leaveRequestRepository.save(request));
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile employee = employeeProfileRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return leaveRequestRepository.findByEmployee(employee).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(String teamName, LocalDate start, LocalDate end) {
        return leaveRequestRepository.findApprovedOverlappingForTeam(teamName, start, end).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LeaveRequestDto convertToDto(LeaveRequest request) {
        return LeaveRequestDto.builder()
                .id(request.getId())
                .employeeId(request.getEmployee().getEmployeeId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .type(request.getType())
                .status(request.getStatus())
                .reason(request.getReason())
                .build();
    }
}