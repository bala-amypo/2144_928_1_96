package com.example.demo.service.impl;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {
    
    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    
    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository teamCapacityConfigRepository) {
        this.teamCapacityConfigRepository = teamCapacityConfigRepository;
    }
    
    @Override
    public TeamCapacityConfig createRule(TeamCapacityConfig rule) {
        if (teamCapacityConfigRepository.existsByTeamName(rule.getTeamName())) {
            throw new BadRequestException("Capacity rule already exists for team: " + rule.getTeamName());
        }
        
        validateRule(rule);
        
        return teamCapacityConfigRepository.save(rule);
    }
    
    @Override
    public TeamCapacityConfig updateRule(Long id, TeamCapacityConfig updatedRule) {
        TeamCapacityConfig existingRule = teamCapacityConfigRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Capacity rule not found with id: " + id));
        
        // If team name is being changed, check if new name already exists
        if (!existingRule.getTeamName().equals(updatedRule.getTeamName()) &&
            teamCapacityConfigRepository.existsByTeamName(updatedRule.getTeamName())) {
            throw new BadRequestException("Capacity rule already exists for team: " + updatedRule.getTeamName());
        }
        
        validateRule(updatedRule);
        
        existingRule.setTeamName(updatedRule.getTeamName());
        existingRule.setTotalHeadcount(updatedRule.getTotalHeadcount());
        existingRule.setMinCapacityPercent(updatedRule.getMinCapacityPercent());
        
        return teamCapacityConfigRepository.save(existingRule);
    }
    
    @Override
    @Transactional(readOnly = true)
    public TeamCapacityConfig getRuleByTeam(String teamName) {
        return teamCapacityConfigRepository.findByTeamName(teamName)
            .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found for team: " + teamName));
    }
    
    private void validateRule(TeamCapacityConfig rule) {
        if (rule.getTotalHeadcount() == null || rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount. Must be greater than 0");
        }
        
        if (rule.getMinCapacityPercent() == null || 
            rule.getMinCapacityPercent() < 1 || rule.getMinCapacityPercent() > 100) {
            throw new BadRequestException("Minimum capacity percent must be between 1 and 100");
        }
    }
}