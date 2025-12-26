package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {
    
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                  EmployeeProfileRepository employeeProfileRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeProfileRepository = employeeProfileRepository;
    }
    
    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new BadRequestException("Start date must be on or before end date");
        }
        
        EmployeeProfile employee = employeeProfileRepository.findById(dto.getEmployeeId())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));
        
        if (!employee.getActive()) {
            throw new BadRequestException("Cannot submit leave for inactive employee");
        }
        
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setType(dto.getType());
        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(dto.getReason());
        
        LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
        return mapToDto(saved);
    }
    
    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));
        
        leaveRequest.setStatus("APPROVED");
        LeaveRequest updated = leaveRequestRepository.save(leaveRequest);
        return mapToDto(updated);
    }
    
    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));
        
        leaveRequest.setStatus("REJECTED");
        LeaveRequest updated = leaveRequestRepository.save(leaveRequest);
        return mapToDto(updated);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile employee = employeeProfileRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        
        List<LeaveRequest> leaves = leaveRequestRepository.findByEmployee(employee);
        return leaves.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LeaveRequestDto> getOverlappingForTeam(String teamName, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must be on or before end date");
        }
        
        List<LeaveRequest> leaves = leaveRequestRepository.findApprovedOverlappingForTeam(teamName, start, end);
        return leaves.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private LeaveRequestDto mapToDto(LeaveRequest leaveRequest) {
        return new LeaveRequestDto(
            leaveRequest.getId(),
            leaveRequest.getEmployee().getId(),
            leaveRequest.getStartDate(),
            leaveRequest.getEndDate(),
            leaveRequest.getType(),
            leaveRequest.getStatus(),
            leaveRequest.getReason()
        );
    }
}