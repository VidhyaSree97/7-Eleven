package com.hospital.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.employeeservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findById(int id);

}
