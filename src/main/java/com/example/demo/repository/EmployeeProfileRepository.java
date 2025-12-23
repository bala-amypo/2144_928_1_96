package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.EmployeeProfile;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    List<EmployeeProfile> findByTeamName(String teamName);
}
