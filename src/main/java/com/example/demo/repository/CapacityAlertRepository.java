package com.example.demo.repository;

import com.example.demo.model.CapacityAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityAlertRepository extends JpaRepository<CapacityAlert, Long> {
}