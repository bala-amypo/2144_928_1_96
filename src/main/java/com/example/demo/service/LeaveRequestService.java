package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestService {

    LeaveRequest create(Long employeeId, LeaveRequest request);

    List<LeaveRequest> getByEmployee(Long employeeId);
}
