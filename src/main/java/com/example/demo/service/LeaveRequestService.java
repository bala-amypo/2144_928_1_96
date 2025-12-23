package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestService {
    LeaveRequest createLeaveRequest(Long employeeId,
                                    LeaveRequest request);
}
