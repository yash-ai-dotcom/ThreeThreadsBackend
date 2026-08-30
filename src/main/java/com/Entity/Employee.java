package com.threethreads.inventory.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Credentials & Authentication
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String pin; // Stored as a BCrypt hash

    @Column(nullable = false)
    private String role; // e.g., "ADMIN", "STAFF"

    // Personal Details
    @Column(nullable = false)
    private String fullName;

    private LocalDate dob;
    private Integer age;

    @Column(unique = true, nullable = false)
    private String aadhaarNo;

    // Contact & Background
    @Column(nullable = false)
    private String mobileNo;

    @Column(columnDefinition = "TEXT")
    private String currentAddress;

    @Column(columnDefinition = "TEXT")
    private String permanentAddress;

    private String education;

    // Constructors, Getters, and Setters
    public Employee() {}

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getAadhaarNo() { return aadhaarNo; }
    public void setAadhaarNo(String aadhaarNo) { this.aadhaarNo = aadhaarNo; }
    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
    public String getCurrentAddress() { return currentAddress; }
    public void setCurrentAddress(String currentAddress) { this.currentAddress = currentAddress; }
    public String getPermanentAddress() { return permanentAddress; }
    public void setPermanentAddress(String permanentAddress) { this.permanentAddress = permanentAddress; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
}