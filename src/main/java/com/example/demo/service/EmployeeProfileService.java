package com.example.demo.service;

import com.example.demo.entity.EmployeeProfile;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfile create(EmployeeProfile employee);
    List<EmployeeProfile> getAll();
    EmployeeProfile getByEmployeeId(Long employeeId);
}
