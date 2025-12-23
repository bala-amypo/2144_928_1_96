package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl
        implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    @Override
    public EmployeeProfile create(EmployeeProfile profile) {
        return repository.save(profile);
    }

    @Override
    public EmployeeProfile getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
