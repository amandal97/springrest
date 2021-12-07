package com.springrest.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
