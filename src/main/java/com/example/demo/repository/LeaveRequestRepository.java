package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    @Query("SELECT lr FROM LeaveRequest lr " +
           "JOIN lr.employee ep " +
           "WHERE ep.teamName = :teamName " +
           "AND lr.status = 'APPROVED' " +
           "AND lr.startDate <= :end AND lr.endDate >= :start")
    List<LeaveRequest> findApprovedOverlappingForTeam(
            @Param("teamName") String teamName,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

    // Fix 34: Missing required custom method (used by CapacityAnalysisServiceImpl)
    @Query("SELECT lr FROM LeaveRequest lr " +
           "WHERE lr.status = 'APPROVED' " +
           "AND :date BETWEEN lr.startDate AND lr.endDate " +
           "AND lr.employee.active = true") // Assuming capacity calculation is only for active employees
    List<LeaveRequest> findApprovedOnDate(@Param("date") LocalDate date);
}