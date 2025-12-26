package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository teamCapacityConfigRepository;

    private void validateRule(TeamCapacityConfig rule) {
        if (rule.getTotalHeadcount() == null || rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Total Headcount must be greater than 0.");
        }
        if (rule.getMinCapacityPercent() == null || rule.getMinCapacityPercent() < 1 || rule.getMinCapacityPercent() > 100) {
            throw new BadRequestException("Minimum Capacity Percent must be between 1 and 100.");
        }
    }

    @Override
    @Transactional
    public TeamCapacityConfig createRule(TeamCapacityConfig rule) {
        validateRule(rule);
        
        if (rule.getTeamName() == null || rule.getTeamName().trim().isEmpty()) {
             throw new BadRequestException("Team name cannot be empty.");
        }
        
        // Ensure team name is unique upon creation (handled by repository constraint, but a check improves UX)
        if (teamCapacityConfigRepository.findByTeamName(rule.getTeamName()).isPresent()) {
            throw new BadRequestException("Rule for team '" + rule.getTeamName() + "' already exists.");
        }
        
        return teamCapacityConfigRepository.save(rule);
    }

    @Override
    @Transactional
    public TeamCapacityConfig updateRule(Long id, TeamCapacityConfig updatedRule) {
        validateRule(updatedRule);
        
        TeamCapacityConfig existing = teamCapacityConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found with id: " + id));

        // Update fields
        existing.setTeamName(updatedRule.getTeamName());
        existing.setTotalHeadcount(updatedRule.getTotalHeadcount());
        existing.setMinCapacityPercent(updatedRule.getMinCapacityPercent());

        return teamCapacityConfigRepository.save(existing);
    }

    @Override
    public TeamCapacityConfig getRuleByTeam(String teamName) {
        return teamCapacityConfigRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found."));
    }
}