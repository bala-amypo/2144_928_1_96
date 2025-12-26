package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.LeaveRequest;

public interface LeaveRequestService {
    LeaveRequest applyLeave(LeaveRequestDto dto);
}
