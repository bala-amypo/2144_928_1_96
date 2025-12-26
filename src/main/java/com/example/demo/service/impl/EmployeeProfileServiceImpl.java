package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeProfileServiceImpl implements EmployeeProfileService {
    
    private final EmployeeProfileRepository employeeProfileRepository;
    
    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }
    
    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        if (employeeProfileRepository.existsByEmployeeId(dto.getEmployeeId())) {
            throw new BadRequestException("Employee ID already exists");
        }
        
        if (employeeProfileRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        
        EmployeeProfile employee = new EmployeeProfile();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setTeamName(dto.getTeamName());
        employee.setRole(dto.getRole());
        employee.setActive(true);
        
        EmployeeProfile saved = employeeProfileRepository.save(employee);
        return mapToDto(saved);
    }
    
    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile employee = employeeProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        // Check if employeeId is being changed and if new one exists
        if (!employee.getEmployeeId().equals(dto.getEmployeeId()) &&
            employeeProfileRepository.existsByEmployeeId(dto.getEmployeeId())) {
            throw new BadRequestException("Employee ID already exists");
        }
        
        // Check if email is being changed and if new one exists
        if (!employee.getEmail().equals(dto.getEmail()) &&
            employeeProfileRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setTeamName(dto.getTeamName());
        employee.setRole(dto.getRole());
        
        EmployeeProfile updated = employeeProfileRepository.save(employee);
        return mapToDto(updated);
    }
    
    @Override
    public void deactivate(Long id) {
        EmployeeProfile employee = employeeProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        employee.setActive(false);
        employeeProfileRepository.save(employee);
    }
    
    @Override
    @Transactional(readOnly = true)
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile employee = employeeProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        return mapToDto(employee);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        List<EmployeeProfile> employees = employeeProfileRepository.findByTeamNameAndActiveTrue(teamName);
        return employees.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeProfileDto> getAll() {
        List<EmployeeProfile> employees = employeeProfileRepository.findAll();
        return employees.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private EmployeeProfileDto mapToDto(EmployeeProfile employee) {
        return new EmployeeProfileDto(
            employee.getId(),
            employee.getEmployeeId(),
            employee.getFullName(),
            employee.getEmail(),
            employee.getTeamName(),
            employee.getRole()
        );
    }
}