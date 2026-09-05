package com.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category; // COGS, UTILITIES, PAYROLL, RENT, TRAVEL, MARKETING, LOGISTICS

    private String subCategory; // E.g., Electricity, Fabric Cost, Article Delivery, Wifi

    @Column(nullable = false)
    private Double amount = 0.0;

    @Column(nullable = false)
    private LocalDate expenseDate = LocalDate.now();

    private String paymentMethod; // UPI, CASH, BANK_TRANSFER, CREDIT_CARD
    private String vendorName;
    private String notes;

    public Expense() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getExpenseDate() { return expenseDate; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}