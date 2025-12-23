package com.example.demo.controller;

import com.example.demo.entity.CapacityAlert;
import com.example.demo.service.CapacityAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAlertService service;

    public CapacityAlertController(
            CapacityAlertService service) {
        this.service = service;
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getByTeam(
            @PathVariable String teamName) {
        return service.getByTeam(teamName);
    }
}
