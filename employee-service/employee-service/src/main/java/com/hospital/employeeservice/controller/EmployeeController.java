package com.hospital.employeeservice.controller;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.employeeservice.model.Employee;
import com.hospital.employeeservice.model.EmployeeList;
import com.hospital.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// @GetMapping(path="/getAllEmployees")
	// public List<Employee> getAllEmployees(){
	// List<Employee> employees = new ArrayList<Employee>();
	// employees=employeeService.getAllEmployees();
	// return employees;
	// }

	@GetMapping(path = "/getAllEmployees")
	public EmployeeList getAllEmployees() {
		EmployeeList employeeList = new EmployeeList();
		employeeList.setEmployees(employeeService.getAllEmployees());
		return employeeList;

	}

	@GetMapping(path = "/employeeById/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);

	}

	@PostMapping(path = "/addEmployee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@PutMapping(path = "/updateEmployeeById/{id}")
	public void updateEmployeeById(@PathVariable("id") int id, @RequestBody Employee employee) {
		Employee employeeDetails = employeeService.getEmployeeById(id);
		employeeDetails.setId(employee.getId());
		employeeDetails.setFirstName(employee.getFirstName());
		employeeDetails.setLastName(employee.getLastName());
		employeeDetails.setAge(employee.getAge());
		employeeDetails.setSpeciality(employee.getSpeciality());
		employeeService.updateEmployeeById(employeeDetails);
	}

	@DeleteMapping(path = "/deleteEmployeeById/{id}")
	public void deleteEmployeeById(@PathVariable("id") int id) {
		employeeService.deleteEmployeeById(id);
	}

}
