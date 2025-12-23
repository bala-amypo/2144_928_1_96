package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;

@RestController
@RequestMapping("/leave-requests")
@CrossOrigin
public class LeaveRequestController {

    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/{employeeId}")
    public LeaveRequest createLeave(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequest leaveRequest) {

        return leaveService.createLeave(employeeId, leaveRequest);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeaves(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }

    @PutMapping("/{leaveId}/status/{status}")
    public LeaveRequest updateStatus(
            @PathVariable Long leaveId,
            @PathVariable String status) {

        return leaveService.updateStatus(leaveId, status);
    }
}
