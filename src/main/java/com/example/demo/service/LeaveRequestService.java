package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    
    LeaveRequestDto create(LeaveRequestDto dto);
    LeaveRequestDto approve(Long id);
    LeaveRequestDto reject(Long id);
    
    // FIX: Changed employeeId type from Long to String to resolve compilation errors
    List<LeaveRequestDto> getByEmployee(String employeeId); 
    
    List<LeaveRequestDto> getOverlappingForTeam(String teamName, LocalDate start, LocalDate end);
}