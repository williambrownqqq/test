package com.example.demo.mapper;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.model.Player;
import com.example.demo.model.Team;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    // Convert Player entity to PlayerDTO
    public PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setAge(player.getAge());
        playerDTO.setExperienceMonths(player.getExperienceMonths());

        if (player.getTeam() != null) {
            playerDTO.setTeamId(player.getTeam().getId());
        }

        return playerDTO;
    }

    // Convert PlayerDTO to Player entity
    public Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setAge(playerDTO.getAge());
        player.setExperienceMonths(playerDTO.getExperienceMonths());

        if (playerDTO.getTeamId() != null) {
            Team team = new Team();
            team.setId(playerDTO.getTeamId());
            player.setTeam(team);
        }

        return player;
    }
}