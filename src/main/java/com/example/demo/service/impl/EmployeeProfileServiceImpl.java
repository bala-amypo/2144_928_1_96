package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {}

    public EmployeeProfile create(EmployeeProfileDto dto) {
        return new EmployeeProfile();
    }

    public EmployeeProfile update(long id, EmployeeProfileDto dto) {
        return new EmployeeProfile();
    }

    public EmployeeProfile getById(long id) {
        return new EmployeeProfile();
    }

    public void deactivate(long id) {}

    public List<EmployeeProfile> getAll() {
        return new ArrayList<>();
    }
}
