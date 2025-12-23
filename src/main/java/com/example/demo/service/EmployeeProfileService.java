package com.example.demo.service;

import com.example.demo.entity.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile create(EmployeeProfile profile);
    EmployeeProfile getById(Long id);
}
