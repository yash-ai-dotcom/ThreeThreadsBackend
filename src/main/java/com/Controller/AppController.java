package com.Controller;

import com.Entity.Employee;
import com.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${app.owner.username:ThreeThreadsuser}")
    private String ownerUsername;

    @Value("${app.owner.pin:threethreads@11}")
    private String ownerPin;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username") != null ? request.get("username").trim() : null;

        String providedSecret = request.get("password");
        if (providedSecret == null || providedSecret.trim().isEmpty()) {
            providedSecret = request.get("pin");
        }

        if (username == null || providedSecret == null || providedSecret.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and Password/PIN are required.");
        }

        providedSecret = providedSecret.trim();

        // 1. OWNER CHECK
        if (ownerUsername.equalsIgnoreCase(username) && ownerPin.equals(providedSecret)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", username);
            return ResponseEntity.ok(resp);
        }

        // 2. ADMIN / EMPLOYEE CHECK
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