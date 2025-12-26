package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequestDto dto);

    List<LeaveRequest> getAllLeaves();
}
