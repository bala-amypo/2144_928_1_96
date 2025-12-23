package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-requests")
@CrossOrigin
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    // CREATE leave request for an employee
    @PostMapping("/{employeeId}")
    public LeaveRequestDto createLeave(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequestDto dto) {
        return service.create(employeeId, dto);
    }

    // GET all leave requests
    @GetMapping
    public List<LeaveRequestDto> getAll() {
        return service.getAll();
    }

    // GET leave requests by employee
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequestDto> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }

    // UPDATE status
    @PutMapping("/{id}/status")
    public LeaveRequestDto updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
