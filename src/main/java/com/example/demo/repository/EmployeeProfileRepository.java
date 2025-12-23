package com.example.demo.repository;

import com.example.demo.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(int employeeId);

    List<EmployeeProfile> findByTeamNameAndActiveTrue(String teamName);
}
