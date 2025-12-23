package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.LeaveRequestDto;

public interface LeaveRequestService {
    LeaveRequestDto create(LeaveRequestDto dto);
    List<LeaveRequestDto> getAll();
    void updateStatus(Long id, String status);
}
