package com.hospital.admissionservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hospital.admissionservice.model.DiseasesList;
import com.hospital.admissionservice.model.Employee;
import com.hospital.admissionservice.model.EmployeeList;
import com.hospital.admissionservice.model.Patient;
//import com.hospital.admissionservice.model.Response;
import com.hospital.admissionservice.service.AdmissionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

	@Autowired
	AdmissionService admissionService;

	@Autowired
	private RestTemplate restTemplate;

	// -----------------------------------------GET ALL PHYSICIANS FROM
	// EMPLOYEE-SERVICE--------------------------------------------

	@HystrixCommand(fallbackMethod = "getPhysicians", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"), })

	@GetMapping(path = "/physicians")
	public EmployeeList getPhysicianss() {
		EmployeeList employeeList = restTemplate.getForObject("http://localhost:8052/employee/getAllEmployees",
				EmployeeList.class);
		return employeeList;
	}

	public String getPhysicians() {
		return "fallBack";
	}

	// ********************************************GET ALL PHYSICIANSBYID FROM
	// EMPLOYEE-SERVICE***********************************************************

	@HystrixCommand(fallbackMethod = "getFallBackByPhysicianId", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000") })

	@GetMapping(path = "/getPhysiciansById/{id}")
	public List<Employee> getPhysiciansById(@PathVariable int id) {
		EmployeeList employeeList = restTemplate.getForObject("http://localhost:8052/employee/getAllEmployees",
				EmployeeList.class);
		List<Employee> getPhysicianById = employeeList.getEmployees().stream().filter(emp -> id == emp.getId())
				.collect(Collectors.toList());
		return getPhysicianById;

	}

	public String getFallBackByPhysicianId(@PathVariable int id) {
		return "Takes long time to respond";
	}

	// ********************************************DELETE PHYSICIANSBYID FROM
	// EMPLOYEE-SERVICE***********************************************************
	@HystrixCommand(fallbackMethod = "getFallBackDiseases", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000") })

	@DeleteMapping("deletePhysicianById/{id}")
	public String deleteEmployee(@PathVariable int id) {
		try {
			restTemplate.delete("http://localhost:8052/employee/deleteEmployeeById/{id}", id);
		} catch (Exception e) {
			return "not deleted";
		}
		return "deleted successfully";

	}

	public String getFallBackDiseases(@PathVariable int id) {
		return "Takes long time to respond";
	}

	// ********************************************UPDATEPHYSICIANSBYID FROM
	// EMPLOYEE-SERVICE***********************************************************

	@HystrixCommand(fallbackMethod = "getFallBackupdateByPhysicianId", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000") })

	@PutMapping(path = "/updateByPhysicianId/{id}")
	public String updatePhysiciansById(@PathVariable int id, @RequestBody Employee employee) {
		restTemplate.put("http://localhost:8052/employee/updateEmployeeById/{id}", employee);
		return "updated successfully";

	}

	public String getFallBackupdateByPhysicianId(@PathVariable int id, @RequestBody Employee employee) {
		return "Takes long time to respond";
	}

	@GetMapping(path = "/patient")
	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		patients = admissionService.getAllPatients();
		return patients;
	}

	@GetMapping(path = "/patient/{id}")
	public Patient getPatientById(@PathVariable("id") int id) {
		return admissionService.getPatientById(id);

	}

	@PostMapping(path = "/addPatient")
	public void addPatient(@RequestBody Patient patient) {
		admissionService.addPatient(patient);
	}

	@PutMapping(path = "/updatePatientById/{id}")
	public void updatePatientById(@PathVariable("id") int id, @RequestBody Patient patient) {
		Patient patientDetails = admissionService.getPatientById(id);
		patientDetails.setAge(patient.getAge());
		patientDetails.setId(patient.getId());
		patientDetails.setName(patient.getName());
		patientDetails.setGender(patient.getGender());
		patientDetails.setNationality(patient.getNationality());
		admissionService.updatePatientById(patientDetails);
	}

	@DeleteMapping(path = "/deletePatientById/{id}")
	public void deletePatientById(@PathVariable("id") int id) {
		admissionService.deletePatientById(id);
	}

}
