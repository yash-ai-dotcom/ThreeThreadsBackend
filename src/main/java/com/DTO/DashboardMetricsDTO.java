package com.DTO;

import java.util.ArrayList;
import java.util.List;

public class DashboardMetricsDTO {

    // Inventory Valuation Metrics
    private Integer totalStockPieces = 0;
    private Double totalStockCostValuation = 0.0;
    private Double totalStockSellingValuation = 0.0;
    private Double potentialGrossMargin = 0.0;

    // Sales Performance Metrics
    private Long totalOrdersPlaced = 0L;
    private Integer totalUnitsSold = 0;
    private Double totalRevenue = 0.0;

    // Expense & Net Profit
    private Double totalOperationalExpenses = 0.0;
    private Double netProfitOrLoss = 0.0;

    // Item-level Breakdown
    private List<ArticlePerformanceDTO> articlePerformances = new ArrayList<>();

    public DashboardMetricsDTO() {}

    // --- Getters and Setters ---

    public Integer getTotalStockPieces() {
        return totalStockPieces;
    }

    public void setTotalStockPieces(Integer totalStockPieces) {
        this.totalStockPieces = totalStockPieces;
    }

    public Double getTotalStockCostValuation() {
        return totalStockCostValuation;
    }

    public void setTotalStockCostValuation(Double totalStockCostValuation) {
        this.totalStockCostValuation = totalStockCostValuation;
    }

    public Double getTotalStockSellingValuation() {
        return totalStockSellingValuation;
    }

    public void setTotalStockSellingValuation(Double totalStockSellingValuation) {
        this.totalStockSellingValuation = totalStockSellingValuation;
    }

    public Double getPotentialGrossMargin() {
        return potentialGrossMargin;
    }

    public void setPotentialGrossMargin(Double potentialGrossMargin) {
        this.potentialGrossMargin = potentialGrossMargin;
    }

    public Long getTotalOrdersPlaced() {
        return totalOrdersPlaced;
    }

    public void setTotalOrdersPlaced(Long totalOrdersPlaced) {
        this.totalOrdersPlaced = totalOrdersPlaced;
    }

    public Integer getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public void setTotalUnitsSold(Integer totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalOperationalExpenses() {
        return totalOperationalExpenses;
    }

    public void setTotalOperationalExpenses(Double totalOperationalExpenses) {
        this.totalOperationalExpenses = totalOperationalExpenses;
    }

    public Double getNetProfitOrLoss() {
        return netProfitOrLoss;
    }

    public void setNetProfitOrLoss(Double netProfitOrLoss) {
        this.netProfitOrLoss = netProfitOrLoss;
    }

    public List<ArticlePerformanceDTO> getArticlePerformances() {
        return articlePerformances;
    }

    public void setArticlePerformances(List<ArticlePerformanceDTO> articlePerformances) {
        this.articlePerformances = articlePerformances;
    }
}