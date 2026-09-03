package com.Controller;

import com.Entity.Employee;
import com.Repository.EmployeeRepository;
import com.Security.JwtUtils;
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

    @Autowired
    private JwtUtils jwtUtils;

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

        // 1. OWNER LOGIN CHECK
        if ("threethreadsuser".equalsIgnoreCase(username) && "705850".equals(providedSecret)) {
            String token = jwtUtils.generateToken("threethreadsuser", "OWNER");
            Map<String, Object> resp = new HashMap<>();
            resp.put("role", "OWNER");
            resp.put("username", "threethreadsuser");
            resp.put("token", token);
            return ResponseEntity.ok(resp);
        }

        // 2. DATABASE EMPLOYEE LOOKUP
        Optional<Employee> empOpt = employeeRepository.findByUsernameIgnoreCase(username);

        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            String storedPin = emp.getPin() != null ? emp.getPin().trim() : "";

            if (!storedPin.isEmpty() && storedPin.equals(providedSecret)) {
                String role = emp.getRole() != null ? emp.getRole() : "ADMIN";
                String token = jwtUtils.generateToken(emp.getUsername(), role);

                Map<String, Object> resp = new HashMap<>();
                resp.put("role", role);
                resp.put("username", emp.getUsername());
                resp.put("fullName", emp.getFullName());
                resp.put("token", token);
                return ResponseEntity.ok(resp);
            }
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }
}