package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employee Profiles", description = "CRUD operations for employee data")
public class EmployeeProfileController {
    
    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto created = employeeService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable Long id) {
        employeeService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }
}