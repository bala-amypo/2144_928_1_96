package com.example.demo.controller;

import com.example.demo.model.LeaveRequest;
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

    @PostMapping
    public LeaveRequest applyLeave(@RequestBody LeaveRequest leaveRequest) {
        return service.applyLeave(leaveRequest);
    }

    @GetMapping("/{id}")
    public LeaveRequest getLeaveById(@PathVariable Long id) {
        return service.getLeaveById(id);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return service.getAllLeaves();
    }
}
