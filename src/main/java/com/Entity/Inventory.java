package com.Entity;

import jakarta.persistence.*;

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

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String photo;

    private Integer setTotal = 0;
    private Integer sizeInSet = 0;
    private Integer sizeS = 0;
    private Integer sizeM = 0;
    private Integer sizeL = 0;
    private Integer sizeXL = 0;
    private Integer sizeXXL = 0;

    private Double costPerPiece = 0.0;
    private Double sellingCostPerPiece = 0.0;

    public Inventory() {}

    // Calculation properties expected by React
    public Integer getGrandTotal() {
        int setsTotal = (setTotal != null ? setTotal : 0) * (sizeInSet != null ? sizeInSet : 0);
        int looseTotal = (sizeS != null ? sizeS : 0) + (sizeM != null ? sizeM : 0) +
                (sizeL != null ? sizeL : 0) + (sizeXL != null ? sizeXL : 0) +
                (sizeXXL != null ? sizeXXL : 0);
        return setsTotal + looseTotal;
    }

    public Double getTotalCost() {
        return getGrandTotal() * (costPerPiece != null ? costPerPiece : 0.0);
    }

    public Double getSellingTotalCost() {
        return getGrandTotal() * (sellingCostPerPiece != null ? sellingCostPerPiece : 0.0);
    }

    public Double getProfitMargin() {
        return getSellingTotalCost() - getTotalCost();
    }

    // Getters and Setters
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
    public Integer getSetTotal() { return setTotal; }
    public void setSetTotal(Integer setTotal) { this.setTotal = setTotal; }
    public Integer getSizeInSet() { return sizeInSet; }
    public void setSizeInSet(Integer sizeInSet) { this.sizeInSet = sizeInSet; }
    public Integer getSizeS() { return sizeS; }
    public void setSizeS(Integer sizeS) { this.sizeS = sizeS; }
    public Integer getSizeM() { return sizeM; }
    public void setSizeM(Integer sizeM) { this.sizeM = sizeM; }
    public Integer getSizeL() { return sizeL; }
    public void setSizeL(Integer sizeL) { this.sizeL = sizeL; }
    public Integer getSizeXL() { return sizeXL; }
    public void setSizeXL(Integer sizeXL) { this.sizeXL = sizeXL; }
    public Integer getSizeXXL() { return sizeXXL; }
    public void setSizeXXL(Integer sizeXXL) { this.sizeXXL = sizeXXL; }
    public Double getCostPerPiece() { return costPerPiece; }
    public void setCostPerPiece(Double costPerPiece) { this.costPerPiece = costPerPiece; }
    public Double getSellingCostPerPiece() { return sellingCostPerPiece; }
    public void setSellingCostPerPiece(Double sellingCostPerPiece) { this.sellingCostPerPiece = sellingCostPerPiece; }
}