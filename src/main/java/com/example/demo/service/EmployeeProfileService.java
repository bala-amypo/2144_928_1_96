package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;

import java.util.List;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfileDto dto);

    List<EmployeeProfile> getAllEmployees();

    EmployeeProfile getEmployeeById(Long id);
}
