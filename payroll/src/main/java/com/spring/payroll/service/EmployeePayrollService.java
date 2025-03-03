package com.spring.payroll.service;

import com.spring.payroll.DTO.EmployeePayrollDTO;
import com.spring.payroll.Model.EmployeePayrollData;
import com.spring.payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository; // Inject repository

    // Get all employees
    public List<EmployeePayrollData> getAllEmployees() {
        return employeeRepository.findAll(); // Fetch from database
    }

    // Get employee by ID
    public Optional<EmployeePayrollData> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create new employee (Convert DTO to Model)
    public EmployeePayrollData createEmployee(EmployeePayrollDTO employeeDTO) {
        EmployeePayrollData newEmployee = new EmployeePayrollData(null, employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(newEmployee);
    }

    // Update employee
    public EmployeePayrollData updateEmployee(Long id, EmployeePayrollDTO updatedDTO) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedDTO.getName());
            employee.setSalary(updatedDTO.getSalary());
            return employeeRepository.save(employee); // Save updated employee
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
