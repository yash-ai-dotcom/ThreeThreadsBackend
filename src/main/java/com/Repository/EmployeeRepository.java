package com.threethreads.inventory.repository;

import com.threethreads.inventory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByAadhaarNo(String aadhaarNo);
}