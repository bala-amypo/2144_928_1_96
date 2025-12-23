package com.example.demo.service;

import com.example.demo.entity.EmployeeProfile;

import java.util.List;

public interface EmployeeProfileService {

    EmployeeProfile create(EmployeeProfile employee);

    EmployeeProfile update(Long id, EmployeeProfile employee);

    List<EmployeeProfile> getAll();

    EmployeeProfile deactivate(Long id);
}
