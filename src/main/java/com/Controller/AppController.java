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
        String username = request.get("username") != null ? request.get("username").trim() : "";
        String providedSecret = request.get("password");
        if (providedSecret == null || providedSecret.trim().isEmpty()) {
            providedSecret = request.get("pin");
        }
        providedSecret = (providedSecret != null) ? providedSecret.trim() : "";

        if (username.isEmpty() || providedSecret.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and Password/PIN are required.");
        }

        // 1. OWNER LOGIN CHECK (Updated PIN: 705850)
        if ("threethreadsuser".equalsIgnoreCase(username) && "705850".equals(providedSecret)) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", "threethreadsuser");
            return ResponseEntity.ok(resp);
        }

        // 2. DATABASE EMPLOYEE LOOKUP
        Optional<Employee> empOpt = employeeRepository.findByUsernameIgnoreCase(username);

        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            String storedPin = emp.getPin() != null ? emp.getPin().trim() : "";

            if (!storedPin.isEmpty() && storedPin.equals(providedSecret)) {
                Map<String, Object> resp = new HashMap<>();
                resp.put("role", emp.getRole() != null ? emp.getRole() : "ADMIN");
                resp.put("username", emp.getUsername());
                resp.put("fullName", emp.getFullName());
                return ResponseEntity.ok(resp);
            }
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }
}