package com.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory_items")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String articleNo;
    private String category;
    private String brand;

    @Column(columnDefinition = "TEXT")
    private String photo;

    private Double costPerPiece = 0.0;
    private Double sellingCostPerPiece = 0.0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    private List<ArticleColor> colors = new ArrayList<>();

    public Inventory() {}

    // Calculation Helpers
    public Integer getGrandTotal() {
        return colors.stream().mapToInt(ArticleColor::getColorTotal).sum();
    }

    public Double getTotalCost() {
        return getGrandTotal() * (costPerPiece != null ? costPerPiece : 0.0);
    }

    public Double getSellingTotalCost() {
        return getGrandTotal() * (sellingCostPerPiece != null ? sellingCostPerPiece : 0.0);
    }

    // Standard Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getArticleNo() { return articleNo; }
    public void setArticleNo(String articleNo) { this.articleNo = articleNo; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    public Double getCostPerPiece() { return costPerPiece; }
    public void setCostPerPiece(Double costPerPiece) { this.costPerPiece = costPerPiece; }
    public Double getSellingCostPerPiece() { return sellingCostPerPiece; }
    public void setSellingCostPerPiece(Double sellingCostPerPiece) { this.sellingCostPerPiece = sellingCostPerPiece; }
    public List<ArticleColor> getColors() { return colors; }
    public void setColors(List<ArticleColor> colors) { this.colors = colors; }
}