package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.LeaveRequestService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping
    public LeaveRequest create(@RequestBody LeaveRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest reject(@PathVariable Long id) {
        return service.reject(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }

    @GetMapping("/team")
    public List<LeaveRequest> overlapping(
            @RequestParam String team,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return service.getOverlappingForTeam(team, start, end);
    }
}
