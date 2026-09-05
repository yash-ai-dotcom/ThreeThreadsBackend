package com.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String customerNo; // e.g., CUST-1001

    @Column(nullable = false)
    private String customerName;

    private String shopName;

    @Column(nullable = false)
    private String phoneNo;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String preferredTransport; // Default logistics/transport carrier

    private LocalDateTime createdAt = LocalDateTime.now();

    public Customer() {}

    // Pre-persist hook to auto-generate unique Customer Number
    @PrePersist
    public void generateCustomerNo() {
        if (this.customerNo == null) {
            this.customerNo = "CUST-" + (System.currentTimeMillis() % 1000000);
        }
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerNo() { return customerNo; }
    public void setCustomerNo(String customerNo) { this.customerNo = customerNo; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPreferredTransport() { return preferredTransport; }
    public void setPreferredTransport(String preferredTransport) { this.preferredTransport = preferredTransport; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}