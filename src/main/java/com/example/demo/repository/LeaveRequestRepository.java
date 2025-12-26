package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.List;
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findApprovedOverlappingForTeam(
            String team, LocalDate start, LocalDate end);
}
