package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@Tag(name = "Leave Request", description = "Leave Request Management APIs")
public class LeaveRequestController {
    
    private final LeaveRequestService leaveRequestService;
    
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
    
    @PostMapping
    @Operation(summary = "Submit a new leave request")
    public ResponseEntity<LeaveRequestDto> submitLeave(@RequestBody LeaveRequestDto dto) {
        LeaveRequestDto created = leaveRequestService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve a leave request")
    public ResponseEntity<LeaveRequestDto> approveLeave(@PathVariable Long id) {
        LeaveRequestDto approved = leaveRequestService.approve(id);
        return ResponseEntity.ok(approved);
    }
    
    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject a leave request")
    public ResponseEntity<LeaveRequestDto> rejectLeave(@PathVariable Long id) {
        LeaveRequestDto rejected = leaveRequestService.reject(id);
        return ResponseEntity.ok(rejected);
    }
    
    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get leave requests by employee ID")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequestDto> leaves = leaveRequestService.getByEmployee(employeeId);
        return ResponseEntity.ok(leaves);
    }
    
    @GetMapping("/team-overlap")
    @Operation(summary = "Get overlapping leaves for a team")
    public ResponseEntity<List<LeaveRequestDto>> getOverlappingLeaves(
            @RequestParam String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        List<LeaveRequestDto> leaves = leaveRequestService.getOverlappingForTeam(teamName, start, end);
        return ResponseEntity.ok(leaves);
    }
}