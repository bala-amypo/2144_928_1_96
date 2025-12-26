package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TeamCapacityConfig;

public interface TeamCapacityConfigRepository
        extends JpaRepository<TeamCapacityConfig, Long> {
}
