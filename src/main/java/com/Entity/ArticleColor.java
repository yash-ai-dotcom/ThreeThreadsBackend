package com.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "article_colors")
public class ArticleColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colorName;
    private Integer setTotal = 0;
    private Integer sizeInSet = 0;

    // Loose pieces per size for this color
    private Integer sizeS = 0;
    private Integer sizeM = 0;
    private Integer sizeL = 0;
    private Integer sizeXL = 0;
    private Integer sizeXXL = 0;

    public ArticleColor() {}

    public Integer getColorTotal() {
        int sets = (setTotal != null ? setTotal : 0) * (sizeInSet != null ? sizeInSet : 0);
        int loose = (sizeS != null ? sizeS : 0) + (sizeM != null ? sizeM : 0) +
                (sizeL != null ? sizeL : 0) + (sizeXL != null ? sizeXL : 0) +
                (sizeXXL != null ? sizeXXL : 0);
        return sets + loose;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }
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
}