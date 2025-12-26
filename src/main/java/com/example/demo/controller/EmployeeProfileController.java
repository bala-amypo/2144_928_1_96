package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
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

    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto createdDto = employeeProfileService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto updatedDto = employeeProfileService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }
    
    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable Long id) {
        employeeProfileService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployeeById(@PathVariable Long id) {
        EmployeeProfileDto dto = employeeProfileService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<EmployeeProfileDto>> getEmployeesByTeam(@PathVariable String teamName) {
        List<EmployeeProfileDto> dtoList = employeeProfileService.getByTeam(teamName);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfileDto> dtoList = employeeProfileService.getAll();
        return ResponseEntity.ok(dtoList);
    }
}