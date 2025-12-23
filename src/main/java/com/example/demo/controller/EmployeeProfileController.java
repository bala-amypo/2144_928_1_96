package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.mapper.EmployeeProfileMapper;
import com.example.demo.repository.EmployeeProfileRepository;

@RestController
@RequestMapping("/employee-profiles")
public class EmployeeProfileController {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileController(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfile entity = EmployeeProfileMapper.toEntity(dto);
        EmployeeProfile saved = repository.save(entity);
        return EmployeeProfileMapper.toDto(saved);
    }
}
