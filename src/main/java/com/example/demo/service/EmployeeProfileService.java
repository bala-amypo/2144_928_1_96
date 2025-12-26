package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfileDto deactivate(long id);
    EmployeeProfileDto getById(long id);
    List<EmployeeProfileDto> getByTeam(String team);
    List<EmployeeProfileDto> getAll();
}
