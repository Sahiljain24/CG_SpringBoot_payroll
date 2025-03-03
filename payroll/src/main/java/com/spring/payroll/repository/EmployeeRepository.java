package com.spring.payroll.repository;




import  com.spring.payroll.Model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeePayrollData, Long> {
}
