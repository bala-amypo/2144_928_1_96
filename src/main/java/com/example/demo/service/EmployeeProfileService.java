package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.EmployeeProfileDto;

public interface EmployeeProfileService {
    EmployeeProfileDto save(EmployeeProfileDto dto);
    List<EmployeeProfileDto> getAll();
    EmployeeProfileDto getById(Long id);
    EmployeeProfileDto update(Long id, EmployeeProfileDto dto);
    void delete(Long id);
}
