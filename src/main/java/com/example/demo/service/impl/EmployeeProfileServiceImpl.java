package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

import java.util.List;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfileDto dto) {

        EmployeeProfile emp = new EmployeeProfile();
        emp.setEmployeeId(dto.getEmployeeId());
        emp.setFullName(dto.getFullName());
        emp.setEmail(dto.getEmail());
        emp.setTeamName(dto.getTeamName());
        emp.setRole(dto.getRole());

        return repo.save(emp);
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
}
