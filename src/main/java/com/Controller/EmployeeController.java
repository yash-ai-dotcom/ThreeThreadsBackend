package com.threethreads.inventory.controller;

import com.threethreads.inventory.model.Employee;
import com.threethreads.inventory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${app.owner.username:owner}")
    private String ownerUsername;

    @Value("${app.owner.pin:1234}")
    private String ownerPin;

    // 1. Employee Management Endpoint (Owner creates/saves employee)
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        if (employeeRepository.existsByUsername(employee.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        if (employeeRepository.existsByAadhaarNo(employee.getAadhaarNo())) {
            return ResponseEntity.badRequest().body("Aadhaar Number already registered.");
        }

        // Note: In production, hash the pin before saving (e.g. passwordEncoder.encode(employee.getPin()))
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. Authentication Endpoint (Supports Owner properties & Admin Database records)
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String pin = request.get("pin");

        // Check Hardcoded Owner
        if (ownerUsername.equals(username) && ownerPin.equals(pin)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", ownerUsername);
            return ResponseEntity.ok(resp);
        }

        // Check Employee Database
        Optional<Employee> empOpt = employeeRepository.findByUsername(username);
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            if (emp.getPin().equals(pin)) {
                Map<String, Object> resp = new HashMap<>();
                resp.put("role", emp.getRole());
                resp.put("username", emp.getUsername());
                resp.put("fullName", emp.getFullName());
                return ResponseEntity.ok(resp);
            }
        }

        return ResponseEntity.status(401).body("Invalid Username or PIN.");
    }
}