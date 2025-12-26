package com.example.demo.service;

import java.util.List;
import com.example.demo.model.LeaveRequest;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequest leaveRequest);

    LeaveRequest getLeaveById(Long id);

    List<LeaveRequest> getAllLeaves();
}
