package com.hospital.admissionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.admissionservice.model.Patient;

@Repository
public interface AdmissionRepository extends JpaRepository<Patient, Integer> {

	Patient findById(int id);
}
