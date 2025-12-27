package com.example.demo.service.impl;

import com.example.demo.model.TeamCapacity;
import com.example.demo.service.TeamCapacityService;

import java.util.ArrayList;
import java.util.List;

public class TeamCapacityServiceImpl implements TeamCapacityService {

    public TeamCapacityServiceImpl() {
    }

    @Override
    public TeamCapacity create(TeamCapacity teamCapacity) {
        return teamCapacity;
    }

    @Override
    public TeamCapacity update(Long id, TeamCapacity teamCapacity) {
        return teamCapacity;
    }

    @Override
    public TeamCapacity getById(Long id) {
        return new TeamCapacity();
    }

    @Override
    public List<TeamCapacity> getAll() {
        return new ArrayList<>();
    }
}
