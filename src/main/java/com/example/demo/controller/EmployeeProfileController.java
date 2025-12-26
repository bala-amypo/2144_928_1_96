package com.example.demo.controller;
entity
import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Profile", description = "Employee Profile Management APIs")
public class EmployeeProfileController {
    
    private final EmployeeProfileService employeeProfileService;
    
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new employee profile")
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto created = employeeProfileService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee profile")
    public ResponseEntity<EmployeeProfileDto> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto updated = employeeProfileService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get employee profile by ID")
    public ResponseEntity<EmployeeProfileDto> getEmployeeById(@PathVariable Long id) {
        EmployeeProfileDto employee = employeeProfileService.getById(id);
        return ResponseEntity.ok(employee);
    }
    
    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get employees by team name")
    public ResponseEntity<List<EmployeeProfileDto>> getEmployeesByTeam(@PathVariable String teamName) {
        List<EmployeeProfileDto> employees = employeeProfileService.getByTeam(teamName);
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfileDto> employees = employeeProfileService.getAll();
        return ResponseEntity.ok(employees);
    }
    @DeleteMapping("/{id}/deactivate")
@Operation(summary = "Deactivate an employee")
public ResponseEntity<Void> deactivateEmployee(@PathVariable Long id) {
    employeeProfileService.deactivate(id);
    return ResponseEntity.noContent().build();
}
}