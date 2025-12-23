package com.example.demo.repository;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    List<LeaveRequest> findApprovedOverlappingForTeam(
            String teamName, LocalDate start, LocalDate end);
}
