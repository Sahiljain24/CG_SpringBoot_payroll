package com.spring.payroll.service;



import com.spring.payroll.Model.EmployeePayrollData;
import com.spring.payroll.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<EmployeePayrollData> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<EmployeePayrollData> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create new employee
    public EmployeePayrollData createEmployee(EmployeePayrollData employee) {
        return employeeRepository.save(employee);
    }

    // Update employee
    public EmployeePayrollData updateEmployee(Long id, EmployeePayrollData updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
