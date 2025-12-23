package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeProfile create(EmployeeProfile employee) {
        return repo.save(employee);
    }

    @Override
    public List<EmployeeProfile> getAll() {
        return repo.findAll();
    }

    @Override
    public EmployeeProfile getByEmployeeId(Long employeeId) {
        return repo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
