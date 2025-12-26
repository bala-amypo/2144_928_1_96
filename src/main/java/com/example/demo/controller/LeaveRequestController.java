package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.LeaveRequest;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveRequest applyLeave(@RequestBody LeaveRequestDto dto) {
        return leaveService.applyLeave(dto);
    }

    @GetMapping
    public List<LeaveRequest> getAll() {
        return leaveService.getAllLeaves();
    }
}
