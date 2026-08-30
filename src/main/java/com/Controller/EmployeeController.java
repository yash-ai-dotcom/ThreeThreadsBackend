package com.Controller;

import com.Entity.Employee;
import com.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
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

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}