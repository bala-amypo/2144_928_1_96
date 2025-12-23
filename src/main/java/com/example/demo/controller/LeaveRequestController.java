package com.example.demo.controller;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping("/{employeeId}")
    public LeaveRequest submitLeave(@PathVariable Long employeeId,
                                    @RequestBody LeaveRequest request) {
        return service.create(employeeId, request);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest reject(@PathVariable Long id) {
        return service.reject(id);
    }

    @GetMapping("/employee/{id}")
    public List<LeaveRequest> getByEmployee(@PathVariable Long id) {
        return service.getByEmployee(id);
    }
}
