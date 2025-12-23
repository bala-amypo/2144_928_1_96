package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile e) {
        return repository.save(e);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public EmployeeProfile updateEmployee(Long id, EmployeeProfile updated) {
        EmployeeProfile e = getEmployeeById(id);
        e.setFullName(updated.getFullName());
        e.setTeamName(updated.getTeamName());
        e.setRole(updated.getRole());
        return repository.save(e);
    }

    @Override
    public void deactivateEmployee(Long id) {
        EmployeeProfile e = getEmployeeById(id);
        e.setActive(false);
        repository.save(e);
    }
}

