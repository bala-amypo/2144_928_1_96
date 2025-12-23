package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;

@RestController
@RequestMapping("/leave")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping("/{employeeId}")
    public LeaveRequest create(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequest request) {
        return service.create(employeeId, request);
    }

    @GetMapping("/employee/{id}")
    public List<LeaveRequest> getByEmployee(@PathVariable Long id) {
        return service.getByEmployee(id);
    }
}
