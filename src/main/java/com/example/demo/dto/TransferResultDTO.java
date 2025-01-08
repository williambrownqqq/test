package com.example.demo.dto;

public class TransferResultDTO {
    private Long playerId;
    private Long oldTeamId;
    private Long newTeamId;
    private double transferFee;
    private double commission;
    private double totalCost;

    public TransferResultDTO(Long playerId, Long oldTeamId, Long newTeamId, double transferFee, double commission, double totalCost) {
        this.playerId = playerId;
        this.oldTeamId = oldTeamId;
        this.newTeamId = newTeamId;
        this.transferFee = transferFee;
        this.commission = commission;
        this.totalCost = totalCost;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getOldTeamId() {
        return oldTeamId;
    }

    public void setOldTeamId(Long oldTeamId) {
        this.oldTeamId = oldTeamId;
    }

    public Long getNewTeamId() {
        return newTeamId;
    }

    public void setNewTeamId(Long newTeamId) {
        this.newTeamId = newTeamId;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}