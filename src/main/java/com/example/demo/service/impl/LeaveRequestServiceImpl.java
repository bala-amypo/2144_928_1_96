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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public LeaveRequestDto create(LeaveRequestDto dto) {

        if (dto.startDate.isAfter(dto.endDate)) {
            throw new BadRequestException("Start date invalid");
        }

        EmployeeProfile employee = employeeRepo.findById(dto.employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setStartDate(dto.startDate);
        leave.setEndDate(dto.endDate);
        leave.setType(dto.type);
        leave.setReason(dto.reason);
        leave.setStatus("PENDING");

        LeaveRequest saved = leaveRepo.save(leave);
        dto.id = saved.getId();
        dto.status = saved.getStatus();
        return dto;
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        leave.setStatus("APPROVED");
        leaveRepo.save(leave);
        return mapToDto(leave);
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        leave.setStatus("REJECTED");
        leaveRepo.save(leave);
        return mapToDto(leave);
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return leaveRepo.findByEmployee(employee)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(
            String teamName, LocalDate start, LocalDate end) {

        return leaveRepo
                .findApprovedOverlappingForTeam(teamName, start, end)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private LeaveRequestDto mapToDto(LeaveRequest l) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.id = l.getId();
        dto.employeeId = l.getEmployee().getId();
        dto.startDate = l.getStartDate();
        dto.endDate = l.getEndDate();
        dto.type = l.getType();
        dto.status = l.getStatus();
        dto.reason = l.getReason();
        return dto;
    }
}
