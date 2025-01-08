package com.example.demo.mapper;

import com.example.demo.dto.TeamDTO;
import com.example.demo.model.Team;
import com.example.demo.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public TeamDTO toDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCommissionRate(team.getCommissionRate());
        teamDTO.setAccountBalance(team.getAccountBalance());

        if (team.getPlayers() != null) {
            List<Long> playerIds = team.getPlayers().stream()
                    .map(Player::getId)
                    .collect(Collectors.toList());
            teamDTO.setPlayerIds(playerIds);
        }

        return teamDTO;
    }

    public Team toEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setCommissionRate(teamDTO.getCommissionRate());
        team.setAccountBalance(teamDTO.getAccountBalance());

        return team;
    }
}