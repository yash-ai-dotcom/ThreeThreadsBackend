package com.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long inventoryId;
    private String articleNo;
    private String colorName;
    private Integer setsOrdered = 0;

    // Loose pieces breakdown per size
    private Integer sizeS = 0;
    private Integer sizeM = 0;
    private Integer sizeL = 0;
    private Integer sizeXL = 0;
    private Integer sizeXXL = 0;

    private Integer totalPiecesOrdered = 0;

    // Excluded sizes list (e.g., "XL", "S,XL")
    private String excludedSizes;

    private Double pricePerPiece = 0.0;
    private Double itemTotal = 0.0;

    public OrderItem() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInventoryId() { return inventoryId; }
    public void setInventoryId(Long inventoryId) { this.inventoryId = inventoryId; }

    public String getArticleNo() { return articleNo; }
    public void setArticleNo(String articleNo) { this.articleNo = articleNo; }

    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }

    public Integer getSetsOrdered() { return setsOrdered; }
    public void setSetsOrdered(Integer setsOrdered) { this.setsOrdered = setsOrdered; }

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

    public Integer getTotalPiecesOrdered() { return totalPiecesOrdered; }
    public void setTotalPiecesOrdered(Integer totalPiecesOrdered) { this.totalPiecesOrdered = totalPiecesOrdered; }

    public String getExcludedSizes() { return excludedSizes; }
    public void setExcludedSizes(String excludedSizes) { this.excludedSizes = excludedSizes; }

    public Double getPricePerPiece() { return pricePerPiece; }
    public void setPricePerPiece(Double pricePerPiece) { this.pricePerPiece = pricePerPiece; }

    public Double getItemTotal() { return itemTotal; }
    public void setItemTotal(Double itemTotal) { this.itemTotal = itemTotal; }
}