package com.example.demo.repository;

import com.example.demo.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    // Find active employees by team
    List<EmployeeProfile> findByTeamNameAndActiveTrue(String teamName);

    // Find by employeeId (NOW int, not String)
    Optional<EmployeeProfile> findByEmployeeId(int employeeId);

    // Check if employeeId already exists
    boolean existsByEmployeeId(int employeeId);

    // Check if email already exists
    boolean existsByEmail(String email);
}
