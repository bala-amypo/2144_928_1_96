package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProfile create(EmployeeProfile employee) {
        return repository.save(employee);
    }

    @Override
    public EmployeeProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<EmployeeProfile> getByTeam(String teamName) {
        return repository.findByTeamNameAndActiveTrue(teamName);
    }
}
