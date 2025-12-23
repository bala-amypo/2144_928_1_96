package com.example.demo.repository;

import com.example.demo.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    // ✅ All leave requests of an employee
    List<LeaveRequest> findByEmployeeId(int employeeId);

    // ✅ Approved leaves on a specific date
    @Query("""
        SELECT l FROM LeaveRequest l
        WHERE l.status = 'APPROVED'
        AND :date BETWEEN l.startDate AND l.endDate
    """)
    List<LeaveRequest> findApprovedOnDate(LocalDate date);
}
