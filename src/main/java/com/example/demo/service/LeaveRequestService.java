package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.entity.LeaveRequest;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest create(LeaveRequestDto dto);

    LeaveRequest approve(Long leaveId);

    LeaveRequest reject(Long leaveId);

    List<LeaveRequest> getByEmployee(Long employeeId);

    List<LeaveRequest> getOverlappingForTeam(
            String teamName,
            LocalDate startDate,
            LocalDate endDate
    );
}
