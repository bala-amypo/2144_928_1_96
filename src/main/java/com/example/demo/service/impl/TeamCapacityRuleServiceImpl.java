package com.example.demo.service.impl;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;

import lombok.RequiredArgsConstructor; // Compiler error fix (requires Lombok)
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Compiler error fix (requires Lombok)
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository configRepo;

    @Override
    public TeamCapacityConfig create(TeamCapacityConfig rule) {
        if (rule.getMinCapacityPercent() < 0 || rule.getMinCapacityPercent() > 100) {
            throw new BadRequestException("Min Capacity Percent must be between 0 and 100.");
        }
        // Added guard for totalHeadcount reference from the compiler error
        if (rule.getTotalHeadcount() <= 0) {
             rule.setTotalHeadcount(1); // Set a default value to prevent division by zero in analysis
        }
        return configRepo.save(rule);
    }

    @Override
    public TeamCapacityConfig update(Long id, TeamCapacityConfig rule) {
        TeamCapacityConfig existing = configRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found for id: " + id));

        if (rule.getTeamName() != null) {
            existing.setTeamName(rule.getTeamName());
        }
        if (rule.getMinCapacityPercent() > 0) {
            if (rule.getMinCapacityPercent() < 0 || rule.getMinCapacityPercent() > 100) {
                 throw new BadRequestException("Min Capacity Percent must be between 0 and 100.");
            }
            existing.setMinCapacityPercent(rule.getMinCapacityPercent());
        }
        // Added handling for totalHeadcount reference from the compiler error
        if (rule.getTotalHeadcount() > 0) {
            existing.setTotalHeadcount(rule.getTotalHeadcount());
        }

        return configRepo.save(existing);
    }

    @Override
    public TeamCapacityConfig getByTeamName(String teamName) {
        return configRepo.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found for team: " + teamName));
    }

    @Override
    public List<TeamCapacityConfig> getAll() {
        return configRepo.findAll();
    }
}