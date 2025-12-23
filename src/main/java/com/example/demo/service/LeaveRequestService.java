package com.example.demo.service;

import com.example.demo.entity.LeaveRequest;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest createLeave(Long employeeId, LeaveRequest leaveRequest);

    List<LeaveRequest> getLeavesByEmployee(Long employeeId);

    LeaveRequest updateStatus(Long leaveId, String status);
}
