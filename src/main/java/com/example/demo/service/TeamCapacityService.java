package com.example.demo.service;

import com.example.demo.model.TeamCapacity;
import java.util.List;

public interface TeamCapacityService {

    TeamCapacity create(TeamCapacity teamCapacity);

    TeamCapacity update(Long id, TeamCapacity teamCapacity);

    TeamCapacity getById(Long id);

    List<TeamCapacity> getAll();
}
