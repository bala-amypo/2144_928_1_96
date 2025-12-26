package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TeamCapacity;

public interface TeamCapacityConfigRepository extends JpaRepository<TeamCapacity, Long> {
}
