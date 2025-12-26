package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TeamCapacity;
import com.example.demo.repository.TeamCapacityRepository;
import com.example.demo.service.TeamCapacityService;

@Service
public class TeamCapacityServiceImpl implements TeamCapacityService {

    private final TeamCapacityRepository teamCapacityRepository;

    public TeamCapacityServiceImpl(TeamCapacityRepository teamCapacityRepository) {
        this.teamCapacityRepository = teamCapacityRepository;
    }

    @Override
    public TeamCapacity saveCapacity(TeamCapacity teamCapacity) {
        return teamCapacityRepository.save(teamCapacity);
    }

    @Override
    public TeamCapacity getCapacityById(Long id) {
        return teamCapacityRepository.findById(id).orElse(null);
    }

    @Override
    public List<TeamCapacity> getAllCapacities() {
        return teamCapacityRepository.findAll();
    }
}
