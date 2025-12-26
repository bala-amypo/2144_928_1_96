package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Team;

public interface TeamService {

    Team saveTeam(Team team);

    Team getTeamById(Long id);

    List<Team> getAllTeams();
}
