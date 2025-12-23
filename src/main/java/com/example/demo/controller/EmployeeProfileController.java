package com.example.demo.controller;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeProfile create(@RequestBody EmployeeProfile employee) {
        return service.create(employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/team/{teamName}")
    public List<EmployeeProfile> getByTeam(@PathVariable String teamName) {
        return service.getByTeam(teamName);
    }
}
