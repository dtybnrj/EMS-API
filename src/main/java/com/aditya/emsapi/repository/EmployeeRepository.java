package com.aditya.emsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

