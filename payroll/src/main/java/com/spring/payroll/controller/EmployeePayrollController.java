package com.spring.payroll.controller;




import com.spring.payroll.Model.EmployeePayrollData;
import com.spring.payroll.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    // GET: Retrieve all employees
    @GetMapping("/")
    public List<EmployeePayrollData> getAllEmployees() {
        return employeePayrollService.getAllEmployees();
    }

    // GET: Retrieve employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeePayrollData> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeePayrollData> employee = employeePayrollService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create new employee
    @PostMapping("/create")
    public EmployeePayrollData createEmployee(@RequestBody EmployeePayrollData employee) {
        return employeePayrollService.createEmployee(employee);
    }

    // PUT: Update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeePayrollData> updateEmployee(@PathVariable Long id, @RequestBody EmployeePayrollData updatedEmployee) {
        return ResponseEntity.ok(employeePayrollService.updateEmployee(id, updatedEmployee));
    }

    // DELETE: Delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeePayrollService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
