package com.example.banquedigitale.DTO;

import lombok.Data;

@Data
public class DashboardStatsDTO {
    private Long totalClients;
    private Long totalAccounts;
    private Double globalBalance;
    private Long courantAccounts;
    private Long epargneAccounts;
}