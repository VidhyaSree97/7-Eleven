package com.hospital.admissionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.admissionservice.model.Patient;
import com.hospital.admissionservice.repository.AdmissionRepository;

@Service
public class AdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;

	public List<Patient> getAllPatients() {
		return admissionRepository.findAll();

	}

	public Patient getPatientById(@PathVariable("id") int id) {
		return admissionRepository.findById(id);

	}

	public void addPatient(Patient patient) {
		admissionRepository.save(patient);
	}

	public void deletePatientById(int id) {
		Patient patient = admissionRepository.findById(id);
		admissionRepository.delete(patient);
	}

	public void updatePatientById(Patient patientDetails) {
		admissionRepository.save(patientDetails);

	}

}
