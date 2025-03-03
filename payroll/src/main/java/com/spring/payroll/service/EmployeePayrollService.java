package com.spring.payroll.service;

import com.spring.payroll.DTO.EmployeePayrollDTO;
import com.spring.payroll.Model.EmployeePayrollData;
import com.spring.payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // Lombok activates logging
@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<EmployeePayrollData> getAllEmployees() {
        log.info("Fetching all employees from database");
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<EmployeePayrollData> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id);
    }

    // Create new employee
    public EmployeePayrollData createEmployee(EmployeePayrollDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        EmployeePayrollData newEmployee = new EmployeePayrollData(null, employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(newEmployee);
    }

    // Update employee
    public EmployeePayrollData updateEmployee(Long id, EmployeePayrollDTO updatedDTO) {
        log.info("Updating employee with ID: {}", id);
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedDTO.getName());
            employee.setSalary(updatedDTO.getSalary());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> {
            log.error("Employee with ID {} not found", id);
            return new RuntimeException("Employee not found");
        });
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        log.warn("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }
}
