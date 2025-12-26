package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Leave Requests", description = "Operations for leave applications")
public class LeaveRequestController {
    
    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeave(@RequestBody LeaveRequestDto dto) {
        LeaveRequestDto created = leaveService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequestDto> approveLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.approve(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequestDto> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.reject(id));
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getByEmployee(employeeId));
    }
}