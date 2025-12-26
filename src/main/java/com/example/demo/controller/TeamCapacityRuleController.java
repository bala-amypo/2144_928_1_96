package com.example.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/capacity-rules")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Capacity Rules", description = "Manage team minimum capacity configuration")
public class TeamCapacityRuleController {
    // Controller placeholder
}