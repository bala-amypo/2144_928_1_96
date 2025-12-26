package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.service.LeaveRequestService;
import com.example.demo.dto.LeaveRequestDto;
import java.time.LocalDate;
import java.util.List;

public class LeaveRequestServiceImpl implements LeaveRequestService {

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRepo,
            EmployeeProfileRepository empRepo) {
    }

    @Override public LeaveRequestDto approve(long id) { return null; }
    @Override public LeaveRequestDto reject(long id) { return null; }
    @Override public List<LeaveRequestDto> getByEmployee(long empId) { return List.of(); }
    @Override public List<LeaveRequestDto> getOverlappingForTeam(
            String team, LocalDate start, LocalDate end) { return List.of(); }
}
