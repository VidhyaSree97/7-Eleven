package com.hospital.employeeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.employeeservice.model.Employee;
import com.hospital.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployeeById(Employee employeeDetails) {
		employeeRepository.save(employeeDetails);
	}

	public void deleteEmployeeById(int id) {
		// Employee employee=employeeRepository.findById(id);
		employeeRepository.deleteById(id);

	}

}
