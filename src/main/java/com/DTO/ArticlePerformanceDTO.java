package com.DTO;

public class ArticlePerformanceDTO {
    private Long inventoryId;
    private String articleNo;
    private String category;
    private String brand;
    private Integer inStockQty;
    private Double costPerPiece;
    private Double sellingCostPerPiece;
    private Double totalStockBuyCost;
    private Double totalStockSellValuation;
    private Integer unitsSold = 0;
    private Double totalRevenueGenerated = 0.0;

    public ArticlePerformanceDTO() {}

    // Getters and Setters
    public Long getInventoryId() { return inventoryId; }
    public void setInventoryId(Long inventoryId) { this.inventoryId = inventoryId; }

    public String getArticleNo() { return articleNo; }
    public void setArticleNo(String articleNo) { this.articleNo = articleNo; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Integer getInStockQty() { return inStockQty; }
    public void setInStockQty(Integer inStockQty) { this.inStockQty = inStockQty; }

    public Double getCostPerPiece() { return costPerPiece; }
    public void setCostPerPiece(Double costPerPiece) { this.costPerPiece = costPerPiece; }

    public Double getSellingCostPerPiece() { return sellingCostPerPiece; }
    public void setSellingCostPerPiece(Double sellingCostPerPiece) { this.sellingCostPerPiece = sellingCostPerPiece; }

    public Double getTotalStockBuyCost() { return totalStockBuyCost; }
    public void setTotalStockBuyCost(Double totalStockBuyCost) { this.totalStockBuyCost = totalStockBuyCost; }

    public Double getTotalStockSellValuation() { return totalStockSellValuation; }
    public void setTotalStockSellValuation(Double totalStockSellValuation) { this.totalStockSellValuation = totalStockSellValuation; }

    public Integer getUnitsSold() { return unitsSold; }
    public void setUnitsSold(Integer unitsSold) { this.unitsSold = unitsSold; }

    public Double getTotalRevenueGenerated() { return totalRevenueGenerated; }
    public void setTotalRevenueGenerated(Double totalRevenueGenerated) { this.totalRevenueGenerated = totalRevenueGenerated; }
}