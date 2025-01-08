package com.example.demo.service;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.dto.TransferResultDTO;
import com.example.demo.exceptionHandler.InsufficientFundsException;
import com.example.demo.exceptionHandler.ResourceNotFoundException;
import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));
        return playerMapper.toDTO(player);
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(savedPlayer);
    }

    @Override
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        player.setName(playerDetails.getName());
        player.setAge(playerDetails.getAge());
        player.setExperienceMonths(playerDetails.getExperienceMonths());

        if (playerDetails.getTeamId() != null) {
            Team team = teamRepository.findById(playerDetails.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
            player.setTeam(team);
        }

        Player updatedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(updatedPlayer);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));
        playerRepository.delete(player);
    }

    @Override
    public TransferResultDTO transferPlayer(Long playerId, Long newTeamId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));
        Team newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        Team oldTeam = player.getTeam();

        // Calculate transfer fee and commission
        double transferFee = (player.getExperienceMonths() * 100000) / player.getAge();
        double commission = transferFee * (oldTeam.getCommissionRate() / 100);
        double totalCost = transferFee + commission;

        if (newTeam.getAccountBalance() < totalCost) {
            throw new InsufficientFundsException("Not enough funds to complete the transfer");
        }

        // Update balances and player team
        newTeam.setAccountBalance(newTeam.getAccountBalance() - totalCost);
        oldTeam.setAccountBalance(oldTeam.getAccountBalance() + totalCost);
        player.setTeam(newTeam);

        // Save changes
        teamRepository.save(newTeam);
        teamRepository.save(oldTeam);
        playerRepository.save(player);

        // Return transfer details
        return new TransferResultDTO(playerId, oldTeam.getId(), newTeam.getId(), transferFee, commission, totalCost);
    }

}