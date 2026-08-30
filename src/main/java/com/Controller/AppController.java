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
@CrossOrigin(origins = "*") // Allows requests from Vercel & local environments
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${app.owner.username:ThreeThreadsuser}")
    private String ownerUsername;

    @Value("${app.owner.pin:threethreads@11}")
    private String ownerPin;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String pin = request.get("pin");

        if (username == null || pin == null) {
            return ResponseEntity.badRequest().body("Username and Password/PIN are required.");
        }

        // 1. OWNER CHECK (Uses dynamic properties with fallback)
        if (ownerUsername.equals(username) && ownerPin.equals(pin)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", username);
            return ResponseEntity.ok(resp);
        }

        // 2. ADMIN / STAFF CHECK (Database lookup from Employee table)
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