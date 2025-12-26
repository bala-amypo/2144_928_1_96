package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfile create(EmployeeProfileDto dto);
    EmployeeProfile update(long id, EmployeeProfileDto dto);
    EmployeeProfile getById(long id);
    void deactivate(long id);
    List<EmployeeProfile> getAll();
}
