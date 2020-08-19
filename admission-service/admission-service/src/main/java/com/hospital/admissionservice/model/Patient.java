package com.hospital.admissionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patient")
public class Patient {

	@Id
	@Column(name = "p_id")
	private int id;
	@Column(name = "p_name")
	private String name;
	@Column(name = "p_age")
	private int age;
	@Column(name = "p_gender")
	private String gender;
	@Column(name = "p_nationality")
	private String nationality;

	public Patient() {

	}

	public Patient(int id, String name, int age, String gender, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", nationality="
				+ nationality + "]";
	}

}
