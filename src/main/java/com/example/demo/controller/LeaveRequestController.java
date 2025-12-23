package com.example.demo.controller;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
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

    @GetMapping("/{employeeId}")
    public List<LeaveRequest> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }
}
