package com.example.demo.service;

import com.example.demo.exceptionHandler.ResourceNotFoundException;
import com.example.demo.mapper.TeamMapper;
import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found"));
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        team.setName(teamDetails.getName());
        team.setCommissionRate(teamDetails.getCommissionRate());
        team.setAccountBalance(teamDetails.getAccountBalance());
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        teamRepository.delete(team);
    }
}