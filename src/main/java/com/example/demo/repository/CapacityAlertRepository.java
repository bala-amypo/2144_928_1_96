package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TeamCapacity;

public interface CapacityAlertRepository extends JpaRepository<TeamCapacity, Long> {
}
