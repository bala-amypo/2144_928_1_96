package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequestDto approve(long id);
    LeaveRequestDto reject(long id);
    List<LeaveRequestDto> getByEmployee(long empId);
    List<LeaveRequestDto> getOverlappingForTeam(
            String team, LocalDate start, LocalDate end);
}
