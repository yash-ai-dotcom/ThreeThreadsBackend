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

    // Hardcoded Owner Credentials with Fallbacks
    @Value("${app.owner.username:ThreeThreadsuser}")
    private String ownerUsername;

    @Value("${app.owner.pin:threethreads@11}")
    private String ownerPin;

    // 1. Employee Onboarding Endpoint
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        if (employeeRepository.existsByUsername(employee.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        if (employeeRepository.existsByAadhaarNo(employee.getAadhaarNo())) {
            return ResponseEntity.badRequest().body("Aadhaar Number already registered.");
        }

        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. Dual-Role Authentication Endpoint
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String pin = request.get("pin");

        if (username == null || pin == null) {
            return ResponseEntity.badRequest().body("Username and Password/PIN are required.");
        }

        // Check Hardcoded Owner
        if ("ThreeThreadsuser".equals(username) && "threethreads@11".equals(pin)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", username);
            return ResponseEntity.ok(resp);
        }

        // Check Employee Database for Admin/Staff
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

        return ResponseEntity.status(401).body("Invalid Username or Password/PIN.");
    }
}