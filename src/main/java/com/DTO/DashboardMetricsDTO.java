package com.DTO;

import java.util.List;

public class DashboardMetricsDTO {
    // Inventory Valuation Metrics
    private Integer totalStockPieces;
    private Double totalStockCostValuation;
    private Double totalStockSellingValuation;
    private Double potentialGrossMargin;

    // Sales Performance Metrics
    private Long totalOrdersPlaced;
    private Integer totalUnitsSold;
    private Double totalRevenue;

    // Expense & Net Profit
    private Double totalOperationalExpenses;
    private Double netProfitOrLoss;

    // Item-level Performance Breakdown
    private List<ArticlePerformanceDTO> articlePerformances;

    public DashboardMetricsDTO() {}

    // Getters and Setters omitted for brevity...
}