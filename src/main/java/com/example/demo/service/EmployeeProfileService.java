package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfileDto create(EmployeeProfileDto dto);
    EmployeeProfileDto update(Long id, EmployeeProfileDto dto); // Fix 25: Correct signature
    void deactivate(Long id); // Fix 29: Correct signature
    EmployeeProfileDto getById(Long id);
    List<EmployeeProfileDto> getByTeam(String teamName); // Fix 27: Correct return type
    List<EmployeeProfileDto> getAll(); // Fix 28: Correct return type
}