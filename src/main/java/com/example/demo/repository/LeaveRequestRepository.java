package com.example.demo.repository;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // ✅ FIX for your runtime error
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    // ✅ Custom JPQL query (CORRECT)
    @Query(
        "SELECT lr FROM LeaveRequest lr " +
        "WHERE lr.status = 'APPROVED' " +
        "AND lr.teamName = :teamName " +
        "AND lr.startDate <= :endDate " +
        "AND lr.endDate >= :startDate"
    )
    List<LeaveRequest> findApprovedOverlappingForTeam(
            @Param("teamName") String teamName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
