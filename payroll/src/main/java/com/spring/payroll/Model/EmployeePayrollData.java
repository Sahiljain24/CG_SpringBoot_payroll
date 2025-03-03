package com.spring.payroll.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data // Lombok to generate Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Generates no-args constructor
@AllArgsConstructor // Generates constructor with all fields
public class EmployeePayrollData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;
}
