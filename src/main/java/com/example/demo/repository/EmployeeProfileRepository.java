package com.example.demo.repository;

import com.example.demo.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
    Optional<EmployeeProfile> findByEmail(String email);
    List<EmployeeProfile> findByTeamNameAndActiveTrue(String teamName);
    boolean existsByEmployeeId(String employeeId);
    boolean existsByEmail(String email);
}