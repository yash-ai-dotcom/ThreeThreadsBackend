package com.Controller;

import com.Entity.Employee;
import com.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        // Extract parameters safely from JSON payload
        String username = request.get("username") != null ? request.get("username").trim() : "";

        // Accept either "password" or "pin" from frontend JSON payload
        String providedSecret = request.get("password");
        if (providedSecret == null || providedSecret.trim().isEmpty()) {
            providedSecret = request.get("pin");
        }
        providedSecret = (providedSecret != null) ? providedSecret.trim() : "";

        if (username.isEmpty() || providedSecret.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and Password/PIN are required.");
        }

        // 1. HARDCODED OWNER CHECK (Guarantees login works regardless of properties/env settings)
        if ("ThreeThreadsuser".equalsIgnoreCase(username) && "threethreads@11".equals(providedSecret)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", "ThreeThreadsuser");
            return ResponseEntity.ok(resp);
        }

        // 2. ADMIN / EMPLOYEE DATABASE CHECK
        Optional<Employee> empOpt = employeeRepository.findByUsername(username);
        if (!empOpt.isPresent()) {
            empOpt = employeeRepository.findByUsername(username.toLowerCase());
        }

        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            if (emp.getPin() != null && emp.getPin().trim().equals(providedSecret)) {
                Map<String, Object> resp = new HashMap<>();
                resp.put("role", emp.getRole());
                resp.put("username", emp.getUsername());
                resp.put("fullName", emp.getFullName());
                return ResponseEntity.ok(resp);
            }
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }
}