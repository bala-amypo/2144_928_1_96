package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    @PostMapping
    public EmployeeProfile create(@RequestBody EmployeeProfile profile) {
        return service.create(profile);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
