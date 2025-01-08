package com.example.demo.service;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.dto.TransferResultDTO;
import com.example.demo.model.Player;

import java.util.List;

public interface PlayerService {
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(Long id);
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO updatePlayer(Long id, PlayerDTO playerDetails);
    void deletePlayer(Long id);
    TransferResultDTO transferPlayer(Long playerId, Long newTeamId);
}
