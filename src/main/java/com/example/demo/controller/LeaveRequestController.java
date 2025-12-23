package com.example.demo.controller;

import com.example.demo.entity.LeaveRequest;
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

    // CREATE
    @PostMapping("/{employeeId}")
    public LeaveRequest create(@PathVariable Long employeeId,
                               @RequestBody LeaveRequest request) {
        return service.create(employeeId, request);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public LeaveRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET BY EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }

    // GET ALL
    @GetMapping
    public List<LeaveRequest> getAll() {
        return service.getAll();
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public LeaveRequest updateStatus(@PathVariable Long id,
                                     @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
