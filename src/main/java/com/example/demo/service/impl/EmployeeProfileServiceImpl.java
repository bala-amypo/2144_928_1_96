package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeRepo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    private EmployeeProfileDto convertToDto(EmployeeProfile emp) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(emp.getId());
        dto.setEmployeeId(emp.getEmployeeId());
        dto.setFullName(emp.getFullName());
        dto.setEmail(emp.getEmail());
        dto.setTeamName(emp.getTeamName());
        dto.setRole(emp.getRole());
        return dto;
    }
    
    // STEP 4.1: create
    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile emp = new EmployeeProfile();
        emp.setEmployeeId(dto.getEmployeeId());
        emp.setFullName(dto.getFullName());
        emp.setEmail(dto.getEmail());
        emp.setTeamName(dto.getTeamName());
        emp.setRole(dto.getRole());
        emp.setActive(true); // Default
        EmployeeProfile saved = employeeRepo.save(emp);
        return convertToDto(saved);
    }

    // STEP 4.1: update
    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));

        // Update fields based on DTO
        if (dto.getFullName() != null) existing.setFullName(dto.getFullName());
        if (dto.getTeamName() != null) existing.setTeamName(dto.getTeamName());
        if (dto.getRole() != null) existing.setRole(dto.getRole());
        // Note: employeeId and email are typically immutable after creation

        EmployeeProfile updated = employeeRepo.save(existing);
        return convertToDto(updated);
    }

    // STEP 4.1: deactivate
    @Override
    public void deactivate(Long id) {
        EmployeeProfile existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));
        existing.setActive(false);
        employeeRepo.save(existing);
    }

    // STEP 4.1: getById
    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile emp = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));
        return convertToDto(emp);
    }

    // STEP 4.1: getByTeam (uses findByTeamNameAndActiveTrue)
    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return employeeRepo.findByTeamNameAndActiveTrue(teamName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // STEP 4.1: getAll
    @Override
    public List<EmployeeProfileDto> getAll() {
        return employeeRepo.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}