package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // ✅ This method FIXES your compilation error
    @Query("""
        SELECT lr FROM LeaveRequest lr
        WHERE lr.status = 'APPROVED'
        AND :date BETWEEN lr.startDate AND lr.endDate
    """)
    List<LeaveRequest> findApprovedOnDate(@Param("date") LocalDate date);
}
