package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequestDto> submitLeave(@RequestBody LeaveRequestDto dto) {
        LeaveRequestDto createdDto = leaveRequestService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequestDto> approveLeave(@PathVariable Long id) {
        LeaveRequestDto updatedDto = leaveRequestService.approve(id);
        return ResponseEntity.ok(updatedDto);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequestDto> rejectLeave(@PathVariable Long id) {
        LeaveRequestDto updatedDto = leaveRequestService.reject(id);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByEmployee(@PathVariable String employeeId) {
        // FIX: Changed @PathVariable type from Long to String to match the service and models
        List<LeaveRequestDto> dtoList = leaveRequestService.getByEmployee(employeeId);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/team-overlap")
    public ResponseEntity<List<LeaveRequestDto>> getOverlappingLeavesForTeam(
            @RequestParam String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        
        List<LeaveRequestDto> dtoList = leaveRequestService.getOverlappingForTeam(teamName, start, end);
        return ResponseEntity.ok(dtoList);
    }
}