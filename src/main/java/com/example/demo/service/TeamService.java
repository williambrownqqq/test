package com.example.demo.service;

import com.example.demo.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team getTeamById(Long id);
    Team createTeam(Team team);
    Team updateTeam(Long id, Team teamDetails);
    void deleteTeam(Long id);
}
