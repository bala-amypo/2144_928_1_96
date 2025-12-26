package com.example.demo.service.impl;

import com.example.demo.service.EmployeeProfileService;
import com.example.demo.dto.EmployeeProfileDto;
import java.util.*;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    @Override public EmployeeProfileDto deactivate(long id) { return null; }
    @Override public EmployeeProfileDto getById(long id) { return null; }
    @Override public List<EmployeeProfileDto> getByTeam(String team) { return List.of(); }
    @Override public List<EmployeeProfileDto> getAll() { return List.of(); }
}
