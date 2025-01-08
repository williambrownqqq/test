package com.example.demo.controller;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.dto.TransferResultDTO;
import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/new")
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.createPlayer(playerDTO);
    }

    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDetails) {
        return playerService.updatePlayer(id, playerDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

    @PostMapping("/{playerId}/transfer/{newTeamId}")
    public TransferResultDTO transferPlayer(@PathVariable Long playerId, @PathVariable Long newTeamId) {
        return playerService.transferPlayer(playerId, newTeamId);
    }
}