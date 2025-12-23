package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService service;

    @PostMapping("/{employeeId}")
    public LeaveRequest create(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequest request) {

        return service.create(employeeId, request);
    }

    @GetMapping("/{employeeId}")
    public List<LeaveRequest> getByEmployee(
            @PathVariable Long employeeId) {

        return service.getByEmployee(employeeId);
    }
}
