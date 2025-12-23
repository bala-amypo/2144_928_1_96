package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;

public interface EmployeeProfileService {

    EmployeeProfileDto create(EmployeeProfileDto dto);
    EmployeeProfileDto getById(Long id);
}
