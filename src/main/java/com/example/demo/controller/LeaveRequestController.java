package com.example.demo.controller;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody LeaveRequest request) {
        return leaveService.createLeave(employeeId, request);
    }
}
