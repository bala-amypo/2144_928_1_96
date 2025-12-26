package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        // Ensure uniqueness (employeeId and email)
        if (employeeProfileRepository.findByEmployeeId(dto.getEmployeeId()).isPresent()) {
            throw new IllegalArgumentException("Employee ID already exists.");
        }
        if (employeeProfileRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        
        EmployeeProfile employee = convertToEntity(dto);
        employee.setActive(true); // default
        
        return convertToDto(employeeProfileRepository.save(employee));
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        // Update fields
        existing.setFullName(dto.getFullName());
        existing.setEmail(dto.getEmail());
        existing.setTeamName(dto.getTeamName());
        existing.setRole(dto.getRole());
        
        return convertToDto(employeeProfileRepository.save(existing));
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile employee = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        employee.setActive(false);
        employeeProfileRepository.save(employee);
    }
    
    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile employee = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return convertToDto(employee);
    }
    
    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        List<EmployeeProfile> employees = employeeProfileRepository.findByTeamNameAndActiveTrue(teamName);
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        List<EmployeeProfile> employees = employeeProfileRepository.findAll();
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    private EmployeeProfileDto convertToDto(EmployeeProfile entity) {
        return EmployeeProfileDto.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .teamName(entity.getTeamName())
                .role(entity.getRole())
                .build();
    }
    
    private EmployeeProfile convertToEntity(EmployeeProfileDto dto) {
        return EmployeeProfile.builder()
                .employeeId(dto.getEmployeeId())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .teamName(dto.getTeamName())
                .role(dto.getRole())
                .build();
    }
}