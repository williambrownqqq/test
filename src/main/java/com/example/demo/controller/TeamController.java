package com.example.demo.controller;

import com.example.demo.dto.TeamDTO;
import com.example.demo.mapper.TeamMapper;
import com.example.demo.model.Team;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams().stream()
                .map(teamMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeamDTO getTeamById(@PathVariable Long id) {
        return teamMapper.toDTO(teamService.getTeamById(id));
    }

    @PostMapping("/new")
    public TeamDTO createTeam(@RequestBody TeamDTO teamDTO) {
        return teamMapper.toDTO(teamService.createTeam(teamMapper.toEntity(teamDTO)));
    }

    @PutMapping("/{id}")
    public TeamDTO updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        return teamMapper.toDTO(teamService.updateTeam(id, teamMapper.toEntity(teamDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}