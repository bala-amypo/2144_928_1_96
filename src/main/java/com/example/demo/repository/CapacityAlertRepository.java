package com.example.demo.repository;

import com.example.demo.model.CapacityAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapacityAlertRepository extends JpaRepository<CapacityAlert, Long> {

    List<CapacityAlert> findByTeamName(String teamName);
}
