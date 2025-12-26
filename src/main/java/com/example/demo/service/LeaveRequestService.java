package com.example.demo.service;

import com.example.demo.model.LeaveRequest;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequest leaveRequest);

    LeaveRequest getLeaveById(Long id);

    List<LeaveRequest> getAllLeaves();
}
