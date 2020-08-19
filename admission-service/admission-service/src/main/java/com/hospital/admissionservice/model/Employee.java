package com.hospital.admissionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name = "e_id")
	private int id;
	@Column(name = "e_firstName")
	private String firstName;
	@Column(name = "e_lastName")
	private String lastName;
	@Column(name = "e_age")
	private int age;
	@Column(name = "e_speciality")
	private String speciality;

	public Employee() {

	}

	public Employee(int id, String firstName, String lastName, int age, String speciality) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.speciality = speciality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", speciality=" + speciality + "]";
	}

}
