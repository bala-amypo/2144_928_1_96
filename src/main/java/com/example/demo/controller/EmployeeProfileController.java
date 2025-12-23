package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.vv3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    @Operation(summary = "Create a new employee profile")
    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto createdDto = employeeProfileService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing employee profile")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto updatedDto = employeeProfileService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "Get employee profile by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployeeById(@PathVariable Long id) {
        EmployeeProfileDto dto = employeeProfileService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "List all active employees for a specific team")
    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<EmployeeProfileDto>> getEmployeesByTeam(@PathVariable String teamName) {
        List<EmployeeProfileDto> employees = employeeProfileService.getByTeam(teamName);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "List all employee profiles (Admin/Manager view)")
    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfileDto> employees = employeeProfileService.getAll();
        return ResponseEntity.ok(employees);
    }
    
    // Deactivation is usually a separate method in a production environment
    @Operation(summary = "Deactivate an employee profile")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable Long id) {
        employeeProfileService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}