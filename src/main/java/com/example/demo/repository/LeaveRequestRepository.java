package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {
}
