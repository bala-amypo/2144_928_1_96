package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CapacityAlert;

public interface CapacityAlertRepository extends JpaRepository<CapacityAlert, Long> {

    List<CapacityAlert> findByTeamName(String teamName);
}
