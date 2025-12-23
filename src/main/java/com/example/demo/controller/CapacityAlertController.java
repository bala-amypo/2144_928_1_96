package com.example.demo.controller;

import com.example.demo.entity.CapacityAlert;
import com.example.demo.service.CapacityAlertService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAlertService service;

    public CapacityAlertController(CapacityAlertService service) {
        this.service = service;
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getAlerts(
            @PathVariable String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return service.getByTeamAndDateRange(teamName, start, end);
    }
}
