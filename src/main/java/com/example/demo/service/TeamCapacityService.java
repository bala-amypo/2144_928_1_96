package com.example.demo.service;

import java.util.List;
import com.example.demo.model.TeamCapacity;

public interface TeamCapacityService {

    TeamCapacity saveCapacity(TeamCapacity teamCapacity);

    TeamCapacity getCapacityById(Long id);

    List<TeamCapacity> getAllCapacities();
}
